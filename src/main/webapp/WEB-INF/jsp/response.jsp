<%@ page contentType="text/html; charset=UTF-8"%>
<html>

<script>
function goBack() {
    window.history.back();
}
</script>


<head>
<title>Weather Details on ${date}</title>
</head>
<body>

	<h2>Weather Details on ${date}</h2>
	<table>
		<tr>
			<td>City</td>
			<td>${city}</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${description}</td>
		</tr>

		<tr>
			<td>Sunrise</td>
			<td>${sunrise}</td>
		</tr>

		<tr>
			<td>Sunset</td>
			<td>${sunset}</td>
		</tr>


		<tr>
			<td>Average Temperate (Celsius)</td>
			<td>${tempC}</td>
		</tr>

		<tr>
			<td>Average Temperate (Fahrenheit)</td>
			<td>${tempF}</td>
		</tr>

	</table>
	<br>
	
	<button onclick="goBack()">Go Back</button>


</body>
</html>