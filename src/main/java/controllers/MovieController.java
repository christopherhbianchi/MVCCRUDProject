package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.Movie;
import data.MovieDAO;
import data.MovieDAOImpl;

@Controller
@SessionAttributes({"movie", "movieList"})
public class MovieController {
	
	//all the logic is in the DAO and we can access it with this obj
	//so the Autowired tag tells Spring that go find that implementation class
	//of that interface below, it goes out and looks for that Component tag, it grabs that
	//and sticks an object of that right in there!
	@Autowired
	private MovieDAO dao; 
	public int index;
	
	public void setDAO(MovieDAO dao) {
		this.dao = dao;
	}
	public MovieDAO getDAO() {
		return dao;
	}
	
	@RequestMapping(path="index.do", method=RequestMethod.GET) //sends movie list into index
	public ModelAndView goToHomePage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("movieList", dao.getMovies());
		mv.setViewName("index.jsp");
		return mv;
	}
	
	@RequestMapping(path="UpdateMovieScreen.do", method=RequestMethod.GET) //this populates the list in the update movie screen
	public ModelAndView updateMoviePage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("movieList", dao.getMovies());
		mv.setViewName("UpdateMovieForm.jsp");
		return mv;
	}
	
	
//	should i do this now or later. do it now. you can put in names based on what 
//	the method is and fill in in the index later
	@RequestMapping(path="MovieByTitle.do", method=RequestMethod.GET) 
	public ModelAndView getMovieByTitle(@RequestParam("title") String t) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("singleMovieResult.jsp");
		mv.addObject("movie", dao.getMovieByTitle(t));
		return mv;
	}
	
	@RequestMapping(path="MoviesByGenre.do", method=RequestMethod.GET)
	public ModelAndView getMoviesByGenre(@RequestParam("genre") String g) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("movieList", dao.getMoviesByGenre(g)); //this will be a list, can i do this? Yes. A list is an object itself, plus that list has a toString() so it'll show up fine.
		return mv;
	}
	
	@RequestMapping(path="MoviesByYear.do", method=RequestMethod.GET)
	public ModelAndView getMoviesByYear(@RequestParam("year") int y) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("movieList", dao.getMoviesByYear(y)); //this will be a list, can i do this? Yes. A list is an object itself, plus that list has a toString() so it'll show up fine.
		return mv;
	}
	
//	Basically what we're saying here is that when somebody commits an action on a form,
//	we're mapping it to the appropriate java functions in our DaoImpl, running them, returning that list/object
//	to the model and view, then returning the model and view.
	
//	why do we have a Get method in this? I saw we did that in the states version
	@RequestMapping(path="CycleMovies.do", method=RequestMethod.GET)
	public ModelAndView cycleMovies(@RequestParam("cycle") String c, @ModelAttribute Movie movie) { //so this just passes in the ModelAttribute of movie? Yes. But, this means we need to have to declare "movies" as a SessionAttribute tag at the top first.
		ModelAndView mv = new ModelAndView();
		 
		//need something to account for a starting point otherwise it doesn't know which movie it's even on.
		
		if(c.equals("Forward")) {
			mv.addObject("movie", ((MovieDAOImpl) dao).forwardMovie(movie)); //and this just lets us comm with/call it with the EL language
		}
		else {
			mv.addObject("movie",((MovieDAOImpl) dao).backwardMovie(movie));
		}
		mv.setViewName("CycleMoviePage.jsp");
		return mv;
	}
	
	@RequestMapping(path="BeginList.do", method=RequestMethod.GET)
	public ModelAndView beginList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CycleMoviePage.jsp"); //could give it its own jsp page that has cycle buttons on it. And move over cycle functionality from index
		mv.addObject("movie", dao.getMovies().get(0));
		return mv;
	}
	
//	when do we do a post versus a get?
//	when do we do a request param versus not? I see we only need to pass a state in here
	@RequestMapping(path="AddMovie.do", method=RequestMethod.POST)
	public ModelAndView addMovie(Movie m) { //here is where we try to get values from our JSP. So it takes all the parameters from the single form, and then builds a Movie with it, so it's okay it takes an entire "Movie"
		ModelAndView mv = new ModelAndView();

		dao.addMovie(m);
		mv.setViewName("ActionSuccessful.jsp");
//		mv.addObject("movie", movie);
		return mv;
	}
	
	@RequestMapping(path="RemoveMovie.do", method=RequestMethod.POST)
	public ModelAndView removeMovie(@RequestParam("title") String title) {
		ModelAndView mv = new ModelAndView();
		dao.removeMovie(title);
		mv.setViewName("ActionSuccessful.jsp"); //all this says is the view name is this, return this page when we return the MaV object
		mv.addObject("movieList", dao.getMovies());
		//set the dao list with a new list, then return that
		return mv;
	}
	
	@RequestMapping(path="UpdateMovie.do", method=RequestMethod.POST)
	public ModelAndView updateMovie(@RequestParam("title") String title, @RequestParam("genre") String genre
	, @RequestParam("yearReleased") int yearReleased, @RequestParam("leadingActor") String leadingActor
	, @RequestParam("moviePosterURL") String moviePosterURL) {
		ModelAndView mv = new ModelAndView();
		dao.updateMovie(title, genre, yearReleased, leadingActor, moviePosterURL);
		mv.setViewName("ActionSuccessful.jsp");
		//don't need addObject for these because the movies movie list is a field and "movieList" is a session attribute.
				return mv;
	}
//	put this in later, lets go to the index file
	
	
	
	
	
	

}
