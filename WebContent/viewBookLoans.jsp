<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.BookLoans"%>
<%@ page import="com.gcit.training.lms.dao.BookLoansDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	AdministrativeService adminService = new AdministrativeService();
	List<BookLoans> bookLoans = null;
	if(request.getAttribute("bookLoans")!=null){
		bookLoans = (List<BookLoans>)request.getAttribute("bookLoans");
	}else{
		bookLoans = adminService.getAllBookLoans(0, 10,"");
	}
	String searchString = "";
	if(request.getParameter("searchString")!=null)
		searchString = request.getParameter("searchString");
	out.println(bookLoans.size());
%>
<script>
function searchBookLoans(pageNo){	
	$.ajax({
		  url: "searchBookLoans",
		  data: {
			  searchString: $('#searchString').val(),
			  pageNo: pageNo
		  }
		}).done(function(data) {
			$('#bookLoanTable').html(data);
		});
}
</script>

<div class="page-header">
	<h1>List of BookLoans in LMS Application</h1>
	${result }
</div>
<form name="bookLoanForm" method="post">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="searchString" id="searchString" value=<%=searchString%>>
</div>
<button type="submit" class="btn btn-sm btn-primary" onclick="searchBookLoans(1)">Search!</button>
</form>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="javascript:searchBookLoans(1)">1</a></li>
    <li><a href="javascript:searchBookLoans(2)">2</a></li>
    <li><a href="javascript:searchBookLoans(3)">3</a></li>
    <li><a href="javascript:searchBookLoans(4)">4</a></li>
    <li><a href="javascript:searchBookLoans(5)">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="bookLoanTable">
			<thead>
				<tr>
					<th>BookId</th>
					<th>BranchId</th>
					<th>CardNo</th>
					<th>DateIn</th>
					<th>DateOut</th>
					<th>DueDate</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%	
					for (BookLoans bookLoan : bookLoans) {
				%>
				<tr>
					<td><%=bookLoan.getBookId().getBookId()%></td>
					<td><%=bookLoan.getBranchId().getBranchId()%></td>
					<td><%=bookLoan.getCardNo().getCardNo()%></td>
					<td><%=bookLoan.getDateIn()%></td>
					<td><%=bookLoan.getDateOut()%></td>
					<td><%=bookLoan.getDueDate()%></td>
					<td align="center">
					<button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editBookLoans.jsp?bookId=<%=bookLoan.getBookId()%>&branchId=<%=bookLoan.getBranchId() %>">Edit BookLoans</button></td>
					<td>
					<button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='deleteBookLoans.jsp?bookId=<%=bookLoan.getBookId()%>&branchId=<%=bookLoan.getBranchId()%>">Delete BookLoans</button>
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
