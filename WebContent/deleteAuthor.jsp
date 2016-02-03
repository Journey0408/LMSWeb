<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
AdministrativeService adminService = new AdministrativeService();
Integer authorId = Integer.parseInt(request.getParameter("authorId"));
%>
<form action="deleteAuthor" method="get">
	${result}
		<h2>Enter Author details below:</h2>

		<input type="text" name="authorId" value = <%= authorId%> > 
		<button type="submit" class="btn btn-sm btn-primary">Add Author</button>
<body>
</body>
</html>