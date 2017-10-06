package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//this version reads in from a file and then has this sort of tempList that we 
//create from it and store it in Java, and thats what we work with and return

@Component
public class FirstWeekendMovieDAOImpl /*implements MovieDAO*/{
	
	
	
	
	private static final String FILE_NAME="/WEB-INF/movies.txt"; //create this csv file 
	  private List<Movie> movies = new ArrayList<>();
	  
//	  we use the web application context obj to retrieve a servlet context so we 
//	  can read from a file
	  @Autowired
	  private WebApplicationContext wac; 
	  
	  
//	  have to load this up each time the site is accessed
	  @PostConstruct
	  public void init() {
		  try (
//			  InputStreamReader is the only way to read things out of a Web-Inf file. 
//			  It passes in the application context
//			  Input Stream then is the binary flow of data, then the reader can do something with it, 
//			  the bufferedReader then gets the words.
			  InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
			  BufferedReader br = new BufferedReader(new InputStreamReader(is));
			  )
		  {
			  String line;
			  while((line = br.readLine()) != null) {
				  String[] moviePieces = line.split(",");
				  String title = moviePieces[0]; 
				  String genre = moviePieces[1]; 
				  int yearReleased = Integer.parseInt(moviePieces[2]); 
				  String leadingActor = moviePieces[3];
				  String moviePosterURL = moviePieces[4];
				  
				  movies.add(new Movie(title, genre, yearReleased, leadingActor, moviePosterURL));
				  System.out.println(movies.get(0));
			  }
		  }
		  catch(Exception e) {
			  System.err.println(e);
		  }
	  }
	
	
	
//	@Override
//	public Movie getMovieByTitle(String title) {
//		System.out.println(title);
//		for(Movie m : movies) {
//			if (m.getTitle().equals(title)) {
//				return m;
//			}
//		}
//		return null;
//	}
//
//
//	@Override
//	public List<Movie> getMoviesByGenre(String genre){
//		List<Movie> mList = new ArrayList<>();
//		
//		for(Movie m : movies) {
//			if(m.getGenre().equals(genre)) { //we'll make it a dropdown search instead of typing out
//				mList.add(m);
//			}	
//		}
//		
//		return mList;
//	}
//
//	@Override
//	public List<Movie> getMoviesByYear(int yearReleased){
//		List<Movie> mList = new ArrayList<>();
//		
//		for(Movie m : movies) {
//			if(m.getYearReleased() == yearReleased) { //we'll make it a dropdown search instead of typing out
//				mList.add(m);
//			}	
//		}
//		
//		return mList;
//	}
//	
	
	public Movie forwardMovie (Movie m) { //so they can scroll through a list of 10 movies.
		
		System.out.println(m);
		
		if(movies.indexOf(m) == movies.size()-1) {
			m = movies.get(0);
			return m;
		}
		else {
			int i = movies.indexOf(m);
			i++;
			return movies.get(i);
		}
	}
	
	public Movie backwardMovie (Movie m) { //now need to make sure stuff is linked right to pass in appropriate movie as arg
		
		if(0 == movies.indexOf(m)) {
			m = movies.get(movies.size()-1);
			return m;
		}
		else {
			int i = movies.indexOf(m);
			i--;
			return movies.get(i);
		}
	}
	
//
//	@Override
//	public void addMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL) {
//		Movie movie = new Movie(title, genre, yearReleased, leadingActor, moviePosterURL);
//		
//		
//		movies.add(movie); //go back to add functionality to check to see it isn't duplicate, been done above now
//	}
//
//	public void addMovie(Movie m) {
//		movies.add(m); //go back to add functionality to check to see it isn't duplicate, been done above now
//	}
//	
//
//	@Override
//	public void removeMovie(String title) { //will have a drop down that won't let them free type it
//		movies.remove(getMovieByTitle(title)); //updated
//	}
//
//
//
//
//
//	@Override
//	public void updateMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL) {
//		
//		
//		for(int i=0; i< movies.size(); i++) {
//			if(movies.get(i).getTitle().equals(title)) { 
//				
//				movies.get(i).setGenre(genre);
//				movies.get(i).setYearReleased(yearReleased);
//				movies.get(i).setLeadingActor(leadingActor);	
//				movies.get(i).setMoviePosterURL(moviePosterURL);
//			}
//		}
//	}
//	
public List<Movie> getMovies() {
	return movies;
	}

}
