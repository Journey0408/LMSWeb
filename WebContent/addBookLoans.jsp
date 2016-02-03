<%@include file="include.html"%>
<form action="addBookLoans" method="post">
	${result}
	<h2>Enter addBook Copies details below:</h2>

	Book Id: <input type="text" name="bookId"><br> 
	Branch Id:<input type="text" name="branchId"><br> 
	Card Num.:<input type="text" name="cardNo"><br> 
	Date In: <input type="text" name="dateIn"><br> 
	Date Out: <input type="text" name="dateOut"><br> 
	Due Date: <input type="text" name="dueDate"><br>
	<button type="submit" class="btn btn-sm btn-primary">Add
		Author</button>
</form>