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
	<h1>Explore My Top Movie List:</h1>
	<br>
	<img src="http://www.talon.news/wp-content/uploads/2017/04/15HOLLYWOOD-superJumbo-v2.jpg" height="500" width="900">
	<br>
	<br>
	<h3>Get Movie By Name</h3>
	<!-- do i need to put a mapping in the controller to take them to next page? -->
	<form action="MovieByTitle.do" method="GET">
		<select name="title">
			<c:forEach var="movie" items="${movieList}"> 
				<!-- i want to be sure it gives a dropdown of movies, and when its clicked it takes them to a page of that movie -->
				<option value="${movie.title}">${movie.title}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Get Movie"/><br>
		</form>
		<br>
	
	<h3>Get Movies By Genre</h3>
	<form action="MoviesByGenre.do" method="GET">
		<select name="genre">
				<option value="Action">Action</option>
				<option value="Drama">Drama</option>
				<option value="Comedy">Comedy</option>
				<option value="Horror">Horror</option>
		</select>
		<input type="submit" value="Get Movies"/><br>
	</form>
	<br>
		
		
	<h3>Get Movies By Year</h3>
	<form action="MoviesByYear.do" method="GET">
		<select name="year">
				<option value="2006">2006</option>
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2008</option>
				<option value="2010">2010</option>
				<option value="2011">2010</option>
				<option value="2012">2010</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
		</select>
		<input type="submit" value="Get Movies"/><br>
	</form>
	<br>
		
		
	<h3>Begin Movie List</h3> <!-- so we want it to take them to a first movie, and then from there they can cycle forward and backward. We'll make a new method for just beginning the list. -->
	<form action="BeginList.do" method="GET">
		<input type="submit" value="Begin browsing"/><br>
	</form>
	<br>
	<br>
	
	<a href="AddMovie.jsp"><h3>Add Movie</h3></a>
	<br>
	<br>
	<a href="RemoveMovie.jsp"><h3>Remove Movie</h3></a>
	<br>
	<br>
	<a href="UpdateMovieScreen.do"><h3>Update Movie</h3></a> 
		
		
</body>
</html>
