package data;

import java.util.List;

public interface MovieDAO {
	public Movie getMovieByTitle(String title); //what does this need passed in to get this movie by name?
	public List<Movie> getMoviesByGenre(String genre); //these top 2 lines will be our "Reads"
	public List<Movie> getMovies(); //getter for movie list
	public List<Movie> getMoviesByYear(int yearReleased); 
//	public void addMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL);
	public void addMovie(Movie m);
	public void removeMovie(Movie m); 
	public void updateMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL);
	
	
	
	
}


