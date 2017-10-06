# MVC CRUD Movie Project
## Christopher Bianchi

## Assignment
The goal of the assignment is to implement a web project with full CRUD functionality (Create, Read, Update, Delete). CRUD represents the states of persistence that almost every application has. This project is connected to a movie database featuring tables for actor, film, genre, year released and more.

The application is built using the MVC design pattern. Benefits of the MVC pattern is similar to that of encapsulation. There is a separation of concerns between the individual elements that make up the model, view, and controller respectively. Changing code in one has no affect on the code in the others. Their interactions are discussed below.

## My Project
This is an individual application not a public wiki. The user logs in, and is able to explore a list of my favorite/top movies. They can add their own movies with title, genre, year released, leading actor name, and a movie poster url. They can also browse the movie list by various categories, delete from it, and organize it.

## Technologies
We used Spring STS, and Gradle to manage the project dependencies.
The MovieController handles the forms submit by the various views/JSPs, and passes them to the MovieDAOImpl to execute application logic, while also persisting data to the database.
DAOImpl's were created based on functionality rather than by entity.

## Methods
I made it a point for website forms to pass in entire objects to the controller, which could then be used within the impl. Doing so allowed me to pull multiple pieces of information from a single object using getters and setters, rather than setting up multiple "@RequestParams" tags within the controller methods parameters.
In terms of logic, I had methods to pull a film from the database based on it's primary key id, pull films by genre, create films, remove films, and more.
In my DBDAOImpl I put all of the logic for connecting to the database via Connection and DriverManager objects which would actually persist the data via SQL statements typed out as Strings. In future projects, I used JPA, and EntityManager's to persist data, however this project makes use of using SQL queries.


## Future
I would like to create functionality for users to login to the website where they can create a list of favorite movies, and share those lists with their friends. I would implement this by making use of sessions.
