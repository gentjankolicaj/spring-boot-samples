package springboot.samples.jdbctemplate02.service;

import java.util.List;
import springboot.samples.jdbctemplate02.domain.Movie;

public interface MovieService {

  List<Movie> getMovies();

  Movie getMovie(Long movieId);

  void deleteMovie(Long movieId);

  void addMovie(Movie movie);
}
