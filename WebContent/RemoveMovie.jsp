<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remove Movie</title>
<link rel="stylesheet" href="MoviePageDesign.css">
</head>
<body>


<form action="RemoveMovie.do" method="POST">
		<select name="title">
			<c:forEach var="movie" items="${movieList}"> 
				<!-- i want to be sure it gives a dropdown of movies, and when its clicked it takes them to a page of that movie -->
				<option value="${movie.title}">${movie.title}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Remove Movie"/><br>
		</form>
		<a href="index.do">Return Home</a>

</body>
</html>