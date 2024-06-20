package springboot.samples.jdbctemplate01.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.jdbctemplate01.domain.Movie;
import springboot.samples.jdbctemplate01.service.MovieService;

@RestController
@RequestMapping(MovieController.MOVIE_URI)
public class MovieController {

  static final String MOVIE_URI = "api/v1/movie";

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }


  @RequestMapping()
  public List<Movie> getMovies() {
    return movieService.getMovies();
  }

  @GetMapping("{movieId}")
  public Movie getMovieId(@PathVariable("movieId") Long movieId) {
    return movieService.getMovie(movieId);
  }

  @PostMapping
  public void addMovie(@RequestBody Movie movie) {
    movieService.addMovie(movie);
  }

  @DeleteMapping("{movieId}")
  public void deleteMovie(@PathVariable("movieId") Long movieId) {
    movieService.deleteMovie(movieId);
  }

}
