package springboot.samples.jdbctemplate02.service;

import springboot.samples.jdbctemplate02.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();

    Movie getMovie(Long movieId);

    void deleteMovie(Long movieId);

    void addMovie(Movie movie);
}
