<%@include file="include.html"%>
	<form action="addBorrower" method="post">
	${result}
		<h2>Enter Borrower details below:</h2>

		Borrower Name: <input type="text" name="borrowerName"><br>
		Borrower Address: <input type="text" name="borrowerAddress"><br> 
		Borrower Phone: <input type="text" name="borrowerPhone"><br>  
		<button type="submit" class="btn btn-sm btn-primary">Add Author</button>
	</form>
