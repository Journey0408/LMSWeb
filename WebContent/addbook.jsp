<%@include file="include.html"%>
<form action="addBook" method="post">
	${result}
	<h2>Enter Book details below:</h2>

	Book Title: <input type="text" name="title"><br>
	Publisher: <input type="text" name="pulisherId"><br>
	Pick Authors:
	<br /> Pick Authors: <select multiple id="authorIDs">
		<option value="volvo">Volvo</option>
		<option value="saab">Saab</option>
		<option value="opel">Opel</option>
		<option value="audi">Audi</option>
	</select>
	<button type="submit" class="btn btn-sm btn-primary">Add
		Book</button>
</form>
