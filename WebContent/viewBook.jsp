<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>
<%@ page import="com.gcit.training.lms.dao.BookDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Book> books = null;
	if(request.getAttribute("books")!=null){
		books = (List<Book>)request.getAttribute("books");
	}else{
		books = adminService.getAllBook(0, 10,"");
	}
	String searchString = "";
	if(request.getParameter("searchString")!=null)
		searchString = request.getParameter("searchString");
%>
<script>
function searchBook(pageNo){
	//document.forms["authorsForm"].action = "/LMSWeb/searchAuthor?pageNo="+pageNo;
	//document.forms["authorsForm"].submit();
	
	$.ajax({
		  url: "searchBook",
		  data: {
			  searchString: $('#searchString').val(),
			  pageNo: pageNo
		  }
		}).done(function(data) {
			$('#bookTable').html(data);
		});
}
</script>

<div class="page-header">
	<h1>List of Books in LMS Application</h1>
	${result }
</div>
<form name="bookForm" method="post">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="searchString" id="searchString" value=<%=searchString%>>
</div>
<button type="submit" class="btn btn-sm btn-primary" onclick="searchBook(1)">Search!</button>
</form>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="javascript:searchBook(1)">1</a></li>
    <li><a href="javascript:searchBook(2)">2</a></li>
    <li><a href="javascript:searchBook(3)">3</a></li>
    <li><a href="javascript:searchBook(4)">4</a></li>
    <li><a href="javascript:searchBook(5)">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="bookTable">
			<thead>
				<tr>
					<th>#</th>
					<th>Title</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%	
					for (Book book : books) {
				%>
				<tr>
					<td><%=book.getBookId()%></td>
					<td><%=book.getTitle()%></td>
					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editBook.jsp?bookId=<%=book.getBookId()%>">Edit Book</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='deleteBook.jsp?bookId=<%=book.getBookId()%>%>';">Delete Book</button>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</div>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>