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
	<a href="index.jsp">Return Home</a><br>
	<c:forEach var="movie" items="${movieList}">
		<h3>${movie.title}</h3>
		<img src="${movie.moviePosterURL}">
		<h4>Genre: ${movie.genre}</h4>
		<h4>Year Released: ${movie.yearReleased}</h4>
		<h4>Leading Actor: ${movie.leadingActor}</h4>
		<br>
		<br>
	</c:forEach>





</body>
</html>