package springboot.samples.jdbctemplate01.dao;

import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate01.entity.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> selectMovies() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<Movie> selectMovieById(Long movieId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int insertMovie(Movie movie) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int updateMovie(Long movieId, Movie movie) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int deleteMovie(Long movieId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
