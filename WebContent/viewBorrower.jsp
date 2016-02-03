<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Borrower"%>
<%@ page import="com.gcit.training.lms.dao.BorrowerDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Borrower> borrowers = null;
	if(request.getAttribute("borrowers")!=null){
		borrowers = (List<Borrower>)request.getAttribute("borrowers");
	}else{
		borrowers = adminService.getAllBorrowers(0, 10,"");
	}
	String searchString = "";
	if(request.getParameter("searchString")!=null)
		searchString = request.getParameter("searchString");
%>
<script>
function searchBorrower(pageNo){
	//document.forms["authorsForm"].action = "/LMSWeb/searchAuthor?pageNo="+pageNo;
	//document.forms["authorsForm"].submit();
	
	$.ajax({
		  url: "searchBorrower",
		  data: {
			  searchString: $('#searchString').val(),
			  pageNo: pageNo
		  }
		}).done(function(data) {
			$('#borrowersTable').html(data);
		});
}
</script>

<div class="page-header">
	<h1>List of Borrowers in LMS Application</h1>
	${result }
</div>
<form name="borrowersForm" method="post">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="searchString" id="searchString" value=<%=searchString%>>
</div>
<button type="submit" class="btn btn-sm btn-primary" onclick="searchBorrower(1)">Search!</button>
</form>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="javascript:searchBorrower(1)">1</a></li>
    <li><a href="javascript:searchBorrower(2)">2</a></li>
    <li><a href="javascript:searchBorrower(3)">3</a></li>
    <li><a href="javascript:searchBorrower(4)">4</a></li>
    <li><a href="javascript:searchBorrower(5)">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="borrowersTable">
			<thead>
				<tr>
					<th>#</th>
					<th>Borrower Name</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Borrower borrower : borrowers) {
			
				%>
				<tr>
					<td><%=borrower.getCardNo()%></td>
					<td><%=borrower.getName()%></td>
					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editBorrower.jsp?cardNo=<%=borrower.getCardNo()%>">Edit Book</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='deleteBorrower.jsp?cardNo=<%=borrower.getCardNo()%>';">Delete Book</button>
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