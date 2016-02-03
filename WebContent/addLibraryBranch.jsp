<%@include file="include.html"%>
	<form action="addLibraryBranch" method="post">
	${result}
		<h2>Enter Library Branch details below:</h2>

		Branch Name: <input type="text" name="branchName">
		Branch Address: <input type="text" name="branchAddress"> 
		<button type="submit" class="btn btn-sm btn-primary">Library Branch</button>
	</form>
