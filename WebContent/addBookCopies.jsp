<%@include file="include.html"%>
	<form action="addBookCopies" method="post">
	${result}
		<h2>Enter addBook Copies details below:</h2>

		Book Id: <input type="text" name="bookId"><br>
		Branch Id: <input type="text" name="branchId"><br> 
		Copies: <input type="text" name="copies"><br>
		<button type="submit" class="btn btn-sm btn-primary">Add Author</button>
	</form>
