<!DOCTYPE html>
<head>
	<title>Add Student</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<html>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
			
			<!--add student id/ tracking student id, send to servlet -->
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>
			
			<table>
				<tbody>
					<tr>
					<!-- prepopulating the form -->
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName"
						value="${THE_STUDENT.firstName}"/></td>
					</tr>
					
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName"
						value="${THE_STUDENT.lastName}"/></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"
						value="${THE_STUDENT.email}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
		
	</div>


</body>
</html>





















