<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
	table{border-collapse: collapse;}
	td{padding: 10px;}
	td:not(:first-child) {
		text-align: center;
	}
</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23217797f2836f6f7fa43339956423b0"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23217797f2836f6f7fa43339956423b0&libraries=LIBRARY"></script>
<script type="text/javascript">
	var positions=[];
	$(function(){
		
	});
	function mapView(lat,lng){
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(lat, lng),
			level: 5
		};

		var map = new kakao.maps.Map(container, options);
		
		// 마커를 표시할 위치와 title 객체 배열입니다 
	
		// 마커 이미지의 이미지 주소입니다
		    
		for (var i = 0; i < positions.length; i ++) {
		    
			var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage, // 마커 이미지
		      
		    });
		}
	}
	function search(){
		var area=$("input[name=area]").val().trim();
		if(area==null||area==""){
			alert("지역을 입력하세요");
			$("input[name=area]").focus();
		}else{
			$.ajax({
				url:"https://8oi9s0nnth.apigw.ntruss.com/"
				    +"corona19-masks/v1/storesByAddr/json?address="+area,
				dataType:"json",
				success:function(data){
					var mask_stores=data.stores;
					var table=$("<table border='1'>");
					var headerTr=$("<tr>"
					             + "<th>주소</th>"
					             + "<th>생성날짜</th>"
					             + "<th>입고날짜</th>"
					             + "<th>약국이름</th>"
					             + "<th>수량</th>"
								 +	"</tr>");
					table.append(headerTr);
					for (var i = 0; i < mask_stores.length; i++) {
						var tr=$("<tr>");
						tr.append("<td>"
								+"<a target='_blank'  href='https://map.naver.com/v5/search/"
								+mask_stores[i].addr+"'>"
								+mask_stores[i].addr+"</a></td>");
						tr.append("<td>"+mask_stores[i].created_at+"</td>");
						tr.append("<td>"+mask_stores[i].stock_at+"</td>");
						tr.append("<td>"+mask_stores[i].name+"</td>");
						switch (mask_stores[i].remain_stat) {
						case 'empty':
							tr.append("<td title='1개이하' style='color:gray;'>품절임박</td>");
							break;
						case 'few':
							tr.append("<td title='2개~30개' style='color:red;'>부족</td>");
							break;
						case 'some':
							tr.append("<td title='30개~100개' style='color:orange;'>약간남음</td>");
							break;
						case 'break':
							tr.append("<td>판매중지</td>");
							break;
						default:tr.append("<td title='100이상' style='color:green;'>충분</td>");
							break;
						}
						table.append(tr);
						
					}
					$("#container").html(table);
					
					for (var i = 0; i < mask_stores.length; i++) {
						var state="";
						switch (mask_stores[i].remain_stat) {
						case 'empty':
							state="https://www.iconfinder.com/icons/118702/fill_map_pin_icon";
							break;
						case 'few':
							state="https://www.iconfinder.com/icons/299087/map_marker_icon";
							break;
						case 'some':
							state="https://www.iconfinder.com/icons/386706/snap_chat_snapchat_icon";
							break;
						case 'break':
							state="https://www.iconfinder.com/icons/186394/location_pin_icon";
							break;
						default:state="https://www.iconfinder.com/icons/299110/check_sign_icon";
							break;
						}
						var lot={
						        title: mask_stores[i].name, 
						        latlng: new kakao.maps.LatLng(mask_stores[i].lat, mask_stores[i].lng)
						    	,text:state
								};
						positions.push(lot)  

					}
					mapView(mask_stores[0].lat,mask_stores[0].lng);
				}
			})
		}
	}
</script>
</head>
<body>
<input size="60" type="text" name="area" placeholder="ex) 서울특별시 영등포구 또는 서울특별시 강남구 논현동" />
<button onclick="search()">조회</button>
<div id="container"></div>
<div id="map" style="width:1000px;height:900px;"></div>

</body>
</html>





