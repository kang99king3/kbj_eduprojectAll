<%@page import="com.hk.board.daos.CalDao"%>
<%@page import="com.hk.board.dtos.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.utils.Util"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
	#calendar{
		border-collapse: collapse;/*테두리를 실선으로 표현하자!!*/
		border:1px solid gray;
	}
	#calendar td{
		width: 80px;
		height: 80px;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	
	a{text-decoration: none;}
	
	a:link, a:visited {color:black;}
	a:active{color:red;}
	
	td img{width: 20px;height:  20px;  }
	th{background-color: orange; color:white;}
	td > p{font-size: 2pt; background-color:orange;
	 margin-bottom: 1px;}
	 
	.cPreview{
		position: absolute;
		left:20px;
		top:-30px;
		background-color: pink;
		width: 40px;
		height: 40px;
		line-height: 40px;
		text-align: center;
		font-weight: bold;
		border-radius: 20px 20px 20px 1px;
	} 
</style>
<script type="text/javascript">
	//값이 1자리이면 두자리로 만들어 주자
	function isTwo(str){
		return str.length<2?"0"+str:str;
	}

	$(function(){
		//클래스명이 countView인 엘리먼트에서 hover이벤트가 발생한다면 function(){}실행해라~~
		
		$(".countView").hover(function(){
			//$(this): hover이벤트가 발생한 a(.countView)태그를 구한다.
			var aCount=$(this);
			var year=$(".y").text().trim();//년
			var month=$(".m").text().trim();//월
			var date=$(this).text().trim();//일
			var yyyyMMdd=year+isTwo(month)+isTwo(date);
// 			alert(yyyyMMdd);
			$.ajax({
				method:"post", //전송방식
				url:"calcountAjax.do", //요청 url
				data:"yyyyMMdd="+yyyyMMdd, //{"id":"hk","yyyyMMdd":yyyyMMdd}
				datatype:"json", //서버로부터 전달받는 값의 타입 정의
				async:false,   //$.ajax()메서드가 비동기로 실행하는거 막기
				success:function(val){//서버와의 통신 성공시 실행, val값은 전송받은 값을 저장
// 					alert(val["count"]);
// 					alert(aCount.prop("tagName"));
					aCount.after("<div class='cPreview'>"+val["count"]+"</div>");
				},
				error:function(){//서버통신 실패시 실행
					alert("서버통신실패!!");
				}
			});
		},function(){
			$(".cPreview").remove();//마우스가 나가면 엘리먼트를 삭제한다.
		});
	});
</script>
</head>
<%
	
	
	//달력의 날짜를 바꾸기 위해 전달된 year와 month 파라미터를 받는다.
	String paramYear=request.getParameter("year");
	String paramMonth=request.getParameter("month");

	Calendar cal=Calendar.getInstance();// new(X)
	int year=cal.get(Calendar.YEAR);
	int month=cal.get(Calendar.MONTH)+1;// 0월~11월
	
	//---달력의 날짜를 변경하는 요청이 오면 처리할 코드
	//요청이 없으면 year와 month를 쓰면되고, 요청이 있으면 paramYear와 paramMonth를 쓰자!!
	if(paramYear!=null){
		year=Integer.parseInt(paramYear);
	}
	if(paramMonth!=null){
		month=Integer.parseInt(paramMonth);
	}
	
	//월이 증가하다가 12에서 13으로 넘어가는 과정에서 14,15,16...증가되는거 처리하기
	if(month>12){
		month=1;
		year++;
	}
	
	//월이 감소하다가 1에서 0또는 -1,-2... 변경되는거 처리하기
	if(month<1){
		month=12;
		year--;
	}
	
	//현재 월의 1일에 대한 요일 구하기: 1~7 --> 1(일), 2(월),.,.,.7(토)
	cal.set(year,month-1,1);//month-1을 하는 이유는 원래 11월이였기 때문에..
	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);//1~7 중에 반환
	
	//현재 월의 마지막 날 구하기
	int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	//현재 달의 일정들을 3개씩 구한 List
	List<CalDto>clist=(List<CalDto>)request.getAttribute("clist");
	
// 	CalDao dao=new CalDao();
// 	List<CalDto>clist1=dao.getCalViewList("hk", year+Util.isTwo(month+""));
%>
<body>
<h1>일정달력보기</h1>
<table border="1" id="calendar">
	<caption>
		<a href="calendar.do?year=<%=year-1%>&month=<%=month%>">◁</a>
		<a href="calendar.do?year=<%=year%>&month=<%=month-1%>">◀</a>
		<span class="y"><%=year%></span>년<span class="m"><%=month%></span>월
		<a href="calendar.do?year=<%=year%>&month=<%=month+1%>">▶</a>
		<a href="calendar.do?year=<%=year+1%>&month=<%=month%>">▷</a>
	</caption>
	<tr>
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
	<tr>
		<%
			for(int i=0;i<dayOfWeek-1;i++){
				out.print("<td>&nbsp;</td>");
			}
			for(int i=1;i<=lastDay;i++){
				%>
				<td>
					<a class="countView" style="color:<%=Util.fontColor(i, dayOfWeek)%>" href="calboardlist.do?year=<%=year%>&month=<%=month%>&date=<%=i%>"><%=i%></a>
					<a href="insertcalform.do?year=<%=year%>&month=<%=month%>&date=<%=i%>">
						<img alt="일정추가" src="img/pen.png"/>
					</a>
					<%=Util.getCalViewList(i,clist) %>
				</td>
				<%
				//행을 바꿔주기 --->현재일(i)이 토요일인지 확인:토요일은 공백수+현재날짜==7배수
				if((dayOfWeek-1+i)%7==0){
					out.print("</tr><tr>");
				}
			}
			for(int i=0;i<(7-(dayOfWeek-1+lastDay)%7)%7;i++){
				out.print("<td>&nbsp;</td>");
			}
			
		%>
	</tr>
</table>
</body>
</html>









