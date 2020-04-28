<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>파일업로드</h1>
<form action="FileController01.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="command" value="upload"/>
	<table border="1">
		<tr>
			<th>파일업로드</th>
			<td><input type="file" name="filename" required="required" /></td>
		</tr>
		<tr>
			<th>파일다운로드</th>
			<td><a href="upload/${param.filename}">${param.filename}</a></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="첨부"  />
			</td>
		</tr>
	</table>
</form>
<hr>
<h1>파일업로드(DB에 정보저장)</h1>
<form action="FileController02.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>파일업로드</th>
			<td><input type="file" name="filename" required="required" /></td>
		</tr>
		<tr>
			<th>파일다운로드</th>
			<td><a href="FileController02.do?command=downloadlist">파일목록보기</a></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="첨부"  />
			</td>
		</tr>
	</table>
</form>
<hr>
<h1>파일업로드(DB에 정보저장)</h1>
<form action="FileController03.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>파일업로드</th>
			<td><input type="file" name="filename"  multiple="multiple"/><br><br>
				<input type="file" name="filename2" /><br><br>
				<input type="file" name="filename3" />
			</td>
		</tr>
		<tr>
			<th>파일다운로드</th>
			<td><a href="FileController02.do?command=downloadlist">파일목록보기</a></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="첨부"  />
			</td>
		</tr>
	</table>
</form>
</body>
</html>






