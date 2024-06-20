package springboot.samples.jdbctemplate01.service;

import java.util.List;
import springboot.samples.jdbctemplate01.domain.Movie;

public interface MovieService {

  List<Movie> getMovies();

  Movie getMovie(Long movieId);

  void deleteMovie(Long movieId);

  void addMovie(Movie movie);
}
