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
		<%-- <c:choose>
			<c:when test="">
		</c:choose> 
		thought it would be cool to have an if statement here to put pictures with it.--%>
<!-- had to create a separate jsp to return cuz the other one was only built to print lists -->
		<a href="index.jsp">Return Home</a>
</body>
</html>