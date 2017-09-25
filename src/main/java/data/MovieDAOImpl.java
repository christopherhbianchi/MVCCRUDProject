package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.Statement;



@Component
public class MovieDAOImpl implements MovieDAO{
	
	  private List<Movie> movies = new ArrayList<>();
	  
//	  we use the web application context obj to retrieve a servlet context so we 
//	  can read from a file
	  @Autowired
	  private WebApplicationContext wac; 
	  
	  
		private String url = "jdbc:mysql://localhost:3306/movieapp";
		private String user = "movieapp";
		private String pass = "movieapp"; 
		
		public MovieDAOImpl() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("Error loading MySQL Driver!!!");
			}
		}
	  
//	  have to load this up each time the site is accessed
	  @PostConstruct
	  public void init() { //is the init okay ?
		  movies = getListOfMovies();
	  }
	  
	  public List<Movie> getListOfMovies(){
			List<Movie> theMovieList = new ArrayList<>();
			
			//connections in here and probably each of these
			//back in the original dao, change method calls so that they can call upon this
			//instead of a cheap movieList we load up from a csv file
			
			try {
				System.out.println("in moviedaoimpl getListOfmovies");
				Connection conn = DriverManager.getConnection(url, user, pass);
				String sql = "SELECT * FROM movie"; //only question is will the leading actor id screw me?
				PreparedStatement stmt = conn.prepareStatement(sql); 
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
//					int id = rs.getInt(1); don't need this line to make a temp object in here. this id only exists in the DB
					String title = rs.getString(2); //
					String genre = rs.getString(3);
					int yearReleased = rs.getInt(4);
					String leadingActor = rs.getString(5);
					String moviePosterURL = rs.getString(6);
					
					Movie m = new Movie(title, genre, yearReleased, leadingActor, moviePosterURL);
					theMovieList.add(m);
					System.out.println("MovieDAOImpl: " + m);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return theMovieList;
		}
	
	
	
	//this i believe can be left alone since we'll populate a tempList in here "movies"
	@Override
	public Movie getMovieByTitle(String title) {
		System.out.println(title);
		for(Movie m : movies) {
			if (m.getTitle().equals(title)) {
				return m;
			}
		}
		return null;
	}

	//this i believe can be left alone since we'll populate a tempList in here "movies"
	@Override
	public List<Movie> getMoviesByGenre(String genre){
		List<Movie> mList = new ArrayList<>();
		
		for(Movie m : movies) {
			if(m.getGenre().equals(genre)) { //we'll make it a dropdown search instead of typing out
				mList.add(m);
			}	
		}
		
		return mList;
	}

	//this i believe can be left alone since we'll populate a tempList in here "movies"
	@Override
	public List<Movie> getMoviesByYear(int yearReleased){
		List<Movie> mList = new ArrayList<>();
		
		for(Movie m : movies) {
			if(m.getYearReleased() == yearReleased) { //we'll make it a dropdown search instead of typing out
				mList.add(m);
			}	
		}
		
		return mList;
	}
	
	//this i believe can be left alone since we'll populate a tempList in here "movies"
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
	
	//this i believe can be left alone since we'll populate a tempList in here "movies"
	public Movie backwardMovie (Movie m) { //now need to make sure stuff is linked right to pass in appropriate movie as arg
		
		//can leave this the same, we'll just have that original movie list field updated by db,
		//and then this can reference the temp list,
		//just need a method that keeps refreshing the temp list from the db after an action.
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
	

//	public void addMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL) {
//		Movie movie = new Movie(title, genre, yearReleased, leadingActor, moviePosterURL);
//		movies.add(movie); //this was a duplicate so I can see both ways of adding a movie
//	}
	
	@Override
	public void addMovie(Movie m) {
//		dbDao.addMovieToDB(m);
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO movie(title, genre, year_released, leading_actor, movie_poster_url) "
					+ " VALUES( ?, ?, ?, ?, ? );"; //only question is will the leading actor id screw me?
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, m.getTitle());
			stmt.setString(2, m.getGenre());
			stmt.setInt(3, m.getYearReleased());
			stmt.setString(4, m.getLeadingActor());
			stmt.setString(5, m.getMoviePosterURL());
			
			
			stmt.executeUpdate();
			
//			rs.close();
			stmt.close();
			conn.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		movies = getListOfMovies();
		//only time we need to refresh our "movies" list is when we fundamentally change the DB
	}
	

	@Override
	public void removeMovie(Movie m) { //will have a drop down that won't let them free type it
//		dbDao.removeMovieFromDB(m);
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			String sql = "DELETE FROM movie WHERE title = ?"
					+ " AND genre = ? "
			+ " AND year_released = ? "
			+ " AND leading_actor = ? "
			+ " AND movie_poster_url = ? ;";
//			only question is will the leading actor id screw me?
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, m.getTitle());
			stmt.setString(2, m.getGenre());
			stmt.setInt(3, m.getYearReleased());
			stmt.setString(4, m.getLeadingActor());
			stmt.setString(5, m.getMoviePosterURL());
			
			stmt.executeUpdate();
			
//			rs.close();
			stmt.close();
			conn.close();
		
		} 
		catch (SQLException e) {
		e.printStackTrace();
		}
		movies = getListOfMovies();
	}





	@Override
	public void updateMovie(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL) {
				
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			String sql = "UPDATE movie SET genre = ?, year_released = ?,"
					+ " leading_actor = ?, movie_poster_url = ? "
					+ " WHERE title = ;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,  genre);
			stmt.setInt(2, yearReleased);
			stmt.setString(3,  leadingActor);
			stmt.setString(4,  moviePosterURL);
			stmt.setString(5,  title);
			
			stmt.executeUpdate(); 
			
//			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		movies = getListOfMovies(); //this is to reupdate the list of movies(have a lot of these)

		
//		//this will be taken care of in other DBDaoImpl now
//		//
//		//
//		for(int i=0; i< movies.size(); i++) {
//			if(movies.get(i).getTitle().equals(title)) { 
//				
//				movies.get(i).setGenre(genre);
//				movies.get(i).setYearReleased(yearReleased);
//				movies.get(i).setLeadingActor(leadingActor);	
//				movies.get(i).setMoviePosterURL(moviePosterURL);
//			}
//		}
		
	}
	
public List<Movie> getMovies() {
	movies = getListOfMovies();
	return movies;
	}

}
