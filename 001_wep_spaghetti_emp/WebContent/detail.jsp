<%@page import="com.kbj.web.EmployeeDto"%>
<%@page import="com.kbj.web.LocationsDto"%>
<%@page import="com.kbj.web.DepartMentDto"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	response.setContentType("text/html; charset=euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상세보기</title>
<style type="text/css">
.a {
	background-color: #C00000;
	color: white;
}
input{width:300px}
</style>
<script type="text/javascript">
	function listgo(){
		location.href="employeelist.jsp";
	}
</script>
</head>
<body>
	<%
		String employee_id = request.getParameter("id");

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String passwd = "hr";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		EmployeeDto edto = new EmployeeDto();
		DepartMentDto ddto = new DepartMentDto();
		LocationsDto ldto = new LocationsDto();
		try {
			conn = DriverManager.getConnection(url, user, passwd);
			String sql = "SELECT EMPLOYEE_ID,LAST_NAME,D.DEPARTMENT_NAME,L.STREET_ADDRESS,L.CITY,L.COUNTRY_ID,L.LOCATION_ID,D.DEPARTMENT_ID"
					+ " ,hire_date,email,salary,phone_number"
					+ " FROM EMPLOYEES E JOIN DEPARTMENTS D"
					+ " ON E.DEPARTMENT_ID=D.DEPARTMENT_ID"
					+ " JOIN LOCATIONS L"
					+ " ON D.LOCATION_ID=L.LOCATION_ID"
					+ " WHERE EMPLOYEE_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employee_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				edto.setEmployee_id(rs.getInt("employee_id"));
				edto.setLast_name(rs.getString("last_name"));
				edto.setHire_date(rs.getDate("hire_date"));
				edto.setEmail(rs.getString("email"));
				edto.setSalary(rs.getInt("salary"));
				edto.setPhone_number(rs.getString("phone_number"));
				ddto.setDepartment_name(rs.getString("department_name"));
				ddto.setDepartment_id(rs.getInt("department_id"));
				ldto.setStreet_address(rs.getString("street_address"));
				ldto.setCity(rs.getString("city"));
				ldto.setCountry_id(rs.getString("country_ID"));
				ldto.setLocation_id(rs.getInt("location_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>
	<center>
	<form action="update.jsp" method="post">
		<table border="1">
			<col width="100">
			<col width="300">
			<caption>
				<b>【상 세 정 보】</b>
			</caption>
			<tr>
				<td class="a">사원번호</td>
				<td><input type="text" name="eid" value="<%=edto.getEmployee_id()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">이름</td>
				<td><input type="text" name="lastname" value="<%=edto.getLast_name()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">부서명</td>
				<td><input type="text" name="dname" value="<%=ddto.getDepartment_name()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">근무지역</td>
				<td><input type="text" name="location" value="<%=ldto.getLocation_id()%> &nbsp;<%=ldto.getCountry_id()%>&nbsp;<%=ldto.getCity()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">근무지 주소</td>
				<td><input type="text" name="address"value="<%=ldto.getStreet_address()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">연봉</td>
				<td><input type="text" name="sal" value="<%=edto.getSalary()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">email</td>
				<td><input type="text" name="email"value="<%=edto.getEmail()%>" readonly></td>
			</tr>
			<tr>
				<td class="a">phone</td>
				<td><input type="text" name="phone" value="<%=edto.getPhone_number()%>" readonly></td>
			</tr>
		</table>
		<input type="submit" style="width:50px" value="수정">
		<input type="button" style="width:50px" value="목록" onclick="listgo()">
	</form>
	<hr/>
	<form action="delete.jsp" method="post">
	<input type="hidden" name="eid" value="<%=edto.getEmployee_id()%>">
	<input type="submit" style="width:100px" value="삭제" >
    </form>
    </center>
</body>
</html>








