package data;

import java.util.List;

//so this needs to contain all of our functionality which is going to take actions made by the 
//MovieDAOImpl, and do the updating process in the database
//So far we've put them all in the MovieDAOImpl, so at this stage let's separate these methods
//to make a clearer, task separated java application

public interface DBDAO {
	
	public void addMovieToDB(Movie m);
	public void removeMovieFromDB(Movie m);
//	just to see diff between doing a movie and taking each of it's fields.
	public void updateMovieInDB(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL);
	public List<Movie> getListOfMovies(); //use this to repopulate the tempList for the MovieDAOImpl for things 
//	like cycle movie page
	
	
	
	
	//need a method that takes in a movie, and adds it to the db
	//one that takes in a title, removes it from the db
	//one that returns a list(to the MovieDAOImpl) so it can be returned to the user
	//one that lets the

}
