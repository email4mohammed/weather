<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Enter City</title>
</head>
<body>
	<h2>City</h2>
	<form:form method="POST" action="/weather/getWeather">
		<table>
			<tr>
				<td><form:label path="city">City</form:label></td>
				<td><form:select path="city" items="${listOfCities}" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>