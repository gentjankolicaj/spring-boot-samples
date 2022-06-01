package springboot.jdbctemplate01.dao;

import springboot.jdbctemplate01.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    List<Movie> selectMovies();

    Optional<Movie> selectMovieById(Long movieId);

    int insertMovie(Movie movie);

    int updateMovie(Long movieId, Movie movie);

    int deleteMovie(Long movieId);

}
