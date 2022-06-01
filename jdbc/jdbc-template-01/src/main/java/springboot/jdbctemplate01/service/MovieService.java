package springboot.jdbctemplate01.service;

import springboot.jdbctemplate01.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();

    Movie getMovie(Long movieId);

    void deleteMovie(Integer id);

    void addMovie(Movie movie);
}
