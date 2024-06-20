package springboot.samples.jdbctemplate01.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.jdbctemplate01.dao.MovieDao;
import springboot.samples.jdbctemplate01.domain.Movie;
import springboot.samples.jdbctemplate01.exception.MovieNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {

  private final MovieDao movieDao;

  @Autowired
  public MovieServiceImpl(MovieDao movieDao) {
    this.movieDao = movieDao;
  }

  @Override
  public List<Movie> getMovies() {
    return movieDao.selectMovies();
  }

  @Override
  public Movie getMovie(Long movieId) {
    return movieDao.selectMovieById(movieId).orElseThrow(
        () -> new MovieNotFoundException(String.format("Movie with id %s not found.", movieId)));
  }

  @Override
  public void deleteMovie(Long movieId) {
    int result = movieDao.deleteMovie(movieId);
    if (result != 1) {
      throw new IllegalStateException(
          String.format("Oops couldn't delete movie with id %s", movieId));
    }
  }

  @Override
  public void addMovie(Movie movie) {
    int result = movieDao.insertMovie(movie);
    if (result != 1) {
      throw new IllegalStateException(
          "Oops something went wrong while adding movie");
    }
  }
}
