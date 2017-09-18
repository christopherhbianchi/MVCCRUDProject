<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Top 10 Movie List</title>
<link rel="stylesheet" href="MoviePageDesign.css">
</head>
<body>
	<h3>${movie.title}</h3>
	<h4>Genre: ${movie.genre}</h4>
	<h4>Year Released: ${movie.yearReleased}</h4>
	<h4>Leading Actor: ${movie.leadingActor}</h4>
	<!-- had to create a separate jsp to return cuz the other one was only built to print lists -->
	<form action="CycleMovies.do" name="cycle" method="GET">
		<input type="submit" name="cycle" value="Forward" /><br> <!-- by doing this, when somebody clicks that "forward" button, the name "cycle" is passed to the controller, then the value is pulled from that butotn.-->
		<input type="submit" name="cycle" value="Backward" /><br> <!-- otherwise, if somebody clicked the "backward" button and it had a different name, the method wouldn't be able to pull from "cycle" anymore since that name identifying it didn't exist and there'd be no match, and the value wouldn't get pulled.--> 
	</form>
	<a href="index.jsp">Return Home</a>



</body>
</html>