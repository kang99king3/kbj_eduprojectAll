<%@page import="com.hk.dtos.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.CalDao"%>
<%@page import="com.hk.utils.Util"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
	th{width: 80px;}
	td{height: 80px; vertical-align: top; position: relative;}
	a{text-decoration: none;}
	a:visited {color:black;}
	a:link {color:black;}
	.clist{background: orange;}
	
	td > .count{
		position:absolute;
		top:-20px;
		left:10px;
		display:none;
		background-color: orange;
		width: 30px;
		height: 30px;
		border-radius: 30px 30px 30px 0px;
		text-align: center;
		line-height: 30px;
	}
</style>
<script type="text/javascript">
	$(function(){
		var countA;
		$(".countA").hover(function(){
			
			var spans=$("caption").find("span");
			var year=spans.eq(0).text().trim();
			var month=spans.eq(1).text().trim();
			var date=$(this).text().trim();
			countA=$(this);
			countA.parent().find("span").css("display","block");
			$.ajax({
				url:"CalController.do",
				data:{"command":"calCount","year":year,"month":month,"date":date},
				method:"post",
				dataType:"text",
				success:function(count){
					countA.parent().find("span").text(count);
				}
			});
		},function(){
			countA.parent().find("span").text("");
			countA.parent().find("span").css("display","none");
		});
	});
</script>
</head>
<%
	//요청한 년월을 받는다
	String pYear=request.getParameter("year");
	String pMonth=request.getParameter("month");
	
	Calendar cal=Calendar.getInstance();//Calendar는 new를 사용 못한다.
	int year=cal.get(Calendar.YEAR);//현재 년도를 구함
	int month=cal.get(Calendar.MONTH)+1;//현재 월을 구함(0월~11월)
	
	//년월을 요청했다면 year와 month의 값을 변경하자
	if(pYear!=null){
		year=Integer.parseInt(pYear);
	}
	if(pMonth!=null){
		month=Integer.parseInt(pMonth);
	}
	
	//월중에 12월을 넘어갔을 경우 month는 1월로, 년도는 다음년도로 값을 변경한다.
	if(month>12){
		month=1;
		year++;
	}
	//월중에 1월전을 갔을 경우 month는 12월로, 년도는 전년도로 값을 변경한다.
	if(month<1){
		month=12;
		year--;
	}
	
	//현재 달의 1일에 대한 요일 구하기---> 달력의 처음 시작하는 공백의 수를 구하기 위함
	//해당달의 1일로 Calendar객체를 설정하자
	cal.set(year, month-1, 1);
	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);//일(1)~~토(7)
	
	//해당달의 마지막날 구하기
	int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	//달력에 출력할 일정 3개씩가져오는 코드
	CalDao dao=new CalDao();
	String yyyyMM=year+Util.isTwo(month+"");//yyyyMM 6자리로 변환
	List<CalDto> list=dao.getCalViewList("hk", yyyyMM);
%>
<body>
<h1>일정보기</h1>
<table border="1">
	<caption>
		<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◁</a>
		<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◀</a>
		<span><%=year%></span>년 <span><%=month%></span>월
		<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▶</a>
		<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▷</a>
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
			//공백수 출력하기
			for(int i=0;i<(dayOfWeek-1);i++){
				out.print("<td>&nbsp;</td>");
			}
			//일수 출력하기
			for(int i=1;i<=lastDay;i++){
				%>
				<td>
					<a class="countA" style="color:<%=Util.fontColor(dayOfWeek, i)%>;" href="CalController.do?command=calList&year=<%=year%>&month=<%=month%>&date=<%=i%>">
						<%=i%>
					</a>
					<a href="CalController.do?command=calWrite&year=<%=year%>&month=<%=month%>&date=<%=i%>">
						<img src="img/pen.png" alt="일정추가하기" />
					</a>
					<div style="font-size: 6px;">
						<%=Util.getCalView(list, i) %>
					</div>
					<span class="count"></span>
				</td>
				<%
				// (공백수+현재날짜)%7==0 토요일
				if((dayOfWeek-1+i)%7==0){
					out.print("</tr><tr>");
				}
			}
			
			//달력의 나머지 공백수 출력하기
			int nbsp=(7-(dayOfWeek-1+lastDay)%7)%7;
			for(int i=0;i<nbsp;i++){
				out.print("<td>&nbsp;</td>");
			}
		%>
	</tr>
</table>

</body>
</html>













