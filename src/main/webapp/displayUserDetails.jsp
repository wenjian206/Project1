<%@page import="com.training.ers.dao.LoginDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.training.ers.model.User"%>
<%@page import="com.training.ers.dao.LoginDAOImpl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="styles.css">
</head>
<% String username = (String)session.getAttribute("username"); %>
<h2>You are logged in as : <%= username %> and the message is : <%= session.getAttribute("message") %></h2>
<body>
	<%
	LoginDAO loginDAO = new LoginDAOImpl();
	List<User> users = loginDAO.getUsers();
	Iterator<User> iterator = users.iterator();
	%>
	<h1>List of all the users</h1>
	<table border="2" cellspacing="10" cellpadding="10" class="table table-striped">
	 <thead class="thead-light">
	<tr>
		<th>User Id</th>
		<th>User Name</th>
		<th>Password</th>
		<th>Gender</th>
		<th>Notifications</th>
		<th>Qualifications</th>
	</tr>
		<%
		while (iterator.hasNext()) {
			User user = iterator.next();
		%>
		<tr>
			<td><%=user.getUserId() %></td>
			<td><%=user.getUsername() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getGender() %></td>
			<td><%=user.getNotifications() %></td>
			<td><%=user.getQualification() %></td>
		</tr>
		<%
		}
		%>
	</table>
	<br/>
	<a href="index.jsp">Logout</a>
</body>
</html>