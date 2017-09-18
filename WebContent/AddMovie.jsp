<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Movie</title>
<link rel="stylesheet" href="MoviePageDesign.css">
</head>
<body>
	<h3>Provide Movie Details to Add:</h3>
	<form action="AddMovie.do" method="POST">
		Title:
		<input type="text" name="title"> 
		<br>
		Genre:
		<select name="genre">
				<option value="Action">Action</option>
				<option value="Drama">Drama</option>
				<option value="Comedy">Comedy</option>
				<option value="Horror">Horror</option>
		</select>
		<br>
		Year Released:
		<select name="yearReleased">
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
		<br>
		Leading Actor: 
		<input type="text" name="leadingActor"/>
		<br>
		Movie Poster URL:
		<input type="text" name="moviePosterURL">
		<br>
		<input type="submit" value="Add Movie"/>
	</form>
	
	<a href="index.jsp">Return Home</a>	
	

</body>
</html>