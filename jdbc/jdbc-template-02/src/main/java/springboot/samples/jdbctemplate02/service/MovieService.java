package springboot.samples.jdbctemplate02.service;

import springboot.samples.jdbctemplate02.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();

    Movie getMovie(Long movieId);

    void deleteMovie(Long movieId);

    void addMovie(Movie movie);
}
