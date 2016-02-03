<%@include file="include.html"%>
	<form action="addGenre" method="post">
	${result}
		<h2>Enter Genre details below:</h2>

		Genre Name: <input type="text" name="genreName"> 
		<button type="submit" class="btn btn-sm btn-primary">Add Genre</button>
	</form>
