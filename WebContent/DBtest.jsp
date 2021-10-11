<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.flow.db.DBManager"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
		<table border="3" bordercolor="skyblue">
			<tr bgcolor="skybule">				
				<td>제목</td>
				<td>내용</td>
			</tr>

			<%
				String sql = "select freeboard_title, freeboard_content from Flow_Freeboard";
				
				Connection conn = DBManager.getConnection();
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					out.println("<tr>");
					
					out.println("<tr>"+rs.getString("freeboard_title"));
					out.println("<tr>"+rs.getString("freeboard_content"));
					
					out.println("<tr>");
				}
			%>
		</table>

</body>
</html>