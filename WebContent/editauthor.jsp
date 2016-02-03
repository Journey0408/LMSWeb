<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>

<%
	AdministrativeService adminService = new AdministrativeService();
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	
%>
<div class="modal-content">
	<form action="editAuthor" method="post">
	${result}
		<h2>Enter Author details below:</h2>

		Author Name: <input type="text" name="authorName"> 
		<button type="submit" class="btn btn-sm btn-primary">Add Author</button>
	</form>
</div>