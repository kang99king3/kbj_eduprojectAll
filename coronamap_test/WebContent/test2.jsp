<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			var tmnNm=$("input[name=tmnNm]").val();
			
			$.ajax({
				url:"Test.do",
				method:"post",
				data:{"tmnNm":tmnNm},
				async:false,
				dataType:"xml",
				success:function(data){
					 var empRows=$(data).find("item");
					
					 var table=$("<table border='1'>");
					 for (var i = 0; i < empRows.length; i++) {
						var tr=$("<tr>");
						for (var j = 0; j < empRows.eq(0).children().length; j++) {
							var td=$("<td>");
							td.text(empRows.eq(i).children().eq(j).text());
							tr.append(td);
						}
						table.append(tr);
					}
					 
					 $("body").append(table);
				}
			})
		})
	})
</script>
</head>
<body>
<input type="text" name="tmnNm" value="서울"/>
<button>고고</button>
</body>
</html>