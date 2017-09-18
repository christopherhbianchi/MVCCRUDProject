package data;

public class Movie {
	private String title;
	private String genre;
	private String yearReleased;
	private String leadingActor;
//	private String summary;
//  private String moviePoster; --> we could put the image url as a string, add it as a last field to the txt file, and then use an EL getter to load that up when you select it.
	
	public Movie() {
		
	}
	
	public Movie(String title, String genre, String yearReleased, String leadingActor) {
		super();
		this.title = title;
		this.genre = genre;
		this.yearReleased = yearReleased;
		this.leadingActor = leadingActor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	public String getLeadingActor() {
		return leadingActor;
	}
	public void setLeadingActor(String leadingActor) {
		this.leadingActor = leadingActor;
	}
	
	@Override
	public String toString() {
		return "Movie Title:" + title + "\nGenre:" + genre + "\nYear Released:" + yearReleased + "\nLeadingActor:"
				+ leadingActor;
	}
//	public String getSummary() {
//		return summary;
//	}
//	public void setSummary(String summary) {
//		this.summary = summary;
//	}

}
