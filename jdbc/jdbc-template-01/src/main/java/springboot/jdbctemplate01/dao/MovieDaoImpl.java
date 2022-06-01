package springboot.jdbctemplate01.dao;

import springboot.jdbctemplate01.entity.Movie;

import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> selectMovies() {
        return null;
    }

    @Override
    public Optional<Movie> selectMovieById(Long movieId) {
        return Optional.empty();
    }

    @Override
    public int insertMovie(Movie movie) {
        return 0;
    }

    @Override
    public int updateMovie(Long movieId, Movie movie) {
        return 0;
    }

    @Override
    public int deleteMovie(Long movieId) {
        return 0;
    }
}
