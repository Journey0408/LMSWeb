<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.BookCopies"%>
<%@ page import="com.gcit.training.lms.dao.BookCopiesDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	AdministrativeService adminService = new AdministrativeService();
	List<BookCopies> bookCopies = null;
	if(request.getAttribute("bookCopies")!=null){
		bookCopies = (List<BookCopies>)request.getAttribute("bookCopies");
	}else{
		bookCopies = adminService.getAllBookCopies(0, 10,"");
	}
	String searchString = "";
	if(request.getParameter("searchString")!=null)
		searchString = request.getParameter("searchString");
	out.println(bookCopies.size());
%>
<script>
function searchBookCopies(pageNo){	
	$.ajax({
		  url: "searchBookCopies",
		  data: {
			  searchString: $('#searchString').val(),
			  pageNo: pageNo
		  }
		}).done(function(data) {
			$('#bookCopieTable').html(data);
		});
}
</script>

<div class="page-header">
	<h1>List of BookCopies in LMS Application</h1>
	${result }
</div>
<form name="bookCopieForm" method="post">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="searchString" id="searchString" value=<%=searchString%>>
</div>
<button type="submit" class="btn btn-sm btn-primary" onclick="searchBookCopies(1)">Search!</button>
</form>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="javascript:searchBookCopies(1)">1</a></li>
    <li><a href="javascript:searchBookCopies(2)">2</a></li>
    <li><a href="javascript:searchBookCopies(3)">3</a></li>
    <li><a href="javascript:searchBookCopies(4)">4</a></li>
    <li><a href="javascript:searchBookCopies(5)">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="bookCopieTable">
			<thead>
				<tr>
					<th>BookId</th>
					<th>BranchId</th>
					<th>Copies</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%	
					for (BookCopies bookCopie : bookCopies) {
				%>
				<tr>
					<td><%=bookCopie.getBookId().getBookId()%></td>
					<td><%=bookCopie.getBranchId().getBranchId()%></td>
					<td><%=bookCopie.getNoOfCopies()%></td>
					<td align="center">
					<button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editBookCopies.jsp?bookId=<%=bookCopie.getBookId()%>&branchId=<%=bookCopie.getBranchId() %>">Edit BookCopies</button></td>
					<td>
					<button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='deleteBookCopies.jsp?bookId=<%=bookCopie.getBookId()%>&branchId=<%=bookCopie.getBranchId()%>">Delete BookCopies</button>
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
