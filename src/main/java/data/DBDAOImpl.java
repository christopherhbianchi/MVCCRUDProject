package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.Statement;

@Component
@Primary
public class DBDAOImpl implements DBDAO{
	
	@Autowired
	 private WebApplicationContext wac; 
	
	private String url = "jdbc:mysql://localhost:3306/movieapp";
	private String user = "movieapp";
	private String pass = "movieapp"; 
	
	public DBDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
	
	
	public void addMovieToDB(Movie m) { //good (unsure on url)
		
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
				
//				rs.close();
				stmt.close();
				conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeMovieFromDB(Movie m){ //good (unsure on url)
		
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
			
			ResultSet rs = stmt.executeQuery();
			
			rs.close();
			stmt.close();
			conn.close();
		
		} 
		catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public void updateMovieInDB(String title, String genre, int yearReleased, String leadingActor, String moviePosterURL) {
		
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
			
			ResultSet rs = stmt.executeQuery();
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Movie> getListOfMovies(){
		List<Movie> theMovieList = new ArrayList<>();
		
		//connections in here and probably each of these
		//back in the original dao, change method calls so that they can call upon this
		//instead of a cheap movieList we load up from a csv file
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM movie"; //only question is will the leading actor id screw me?
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
//				int id = rs.getInt(1); don't need this line to make a temp object in here. this id only exists in the DB
				String title = rs.getString(2); //
				String genre = rs.getString(3);
				int yearReleased = rs.getInt(4);
				String leadingActor = rs.getString(5);
				String moviePosterURL = rs.getString(6);
				
				Movie m = new Movie(title, genre, yearReleased, leadingActor, moviePosterURL);
				theMovieList.add(m);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theMovieList;
	}
		

}
