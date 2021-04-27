<%@page import="com.hk.fileupload.dtos.FileDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	List<FileDto> list=(List<FileDto>)request.getAttribute("list");
%>
<body>
<h1>파일목록조회</h1>
<table border="1">
	<tr>
		<th>번호</th><th>원본명</th><th>저장명</th>
		<th>사이즈</th><th>업로드날짜</th><th>삭제여부</th>
	</tr>
	<%if(list==null||list.size()==0){
		out.print("<tr><td>---업로드목록없음---</td></tr>");
	}else{
		for(FileDto dto:list){
			%>
			<tr>
				<td><%=dto.getSeq()%></td>
				<td><a href="download.do?seq=<%=dto.getSeq()%>"><%=dto.getOrigin_fname()%></a></td>
				<td><%=dto.getStored_fname()%></td>
				<td><%=(int)Math.ceil(dto.getFile_size()/(double)1024)%>KB</td>
				<td><%=dto.getF_regdate()%></td>
				<td><%=dto.getF_delflag()%></td>
			</tr>
			<%
		}
	}
	%>
</table>
<img src="upload/7d464cfc0fd040a6b5fb0217f66b71fe.jpg" />
</body>
</html>
		







