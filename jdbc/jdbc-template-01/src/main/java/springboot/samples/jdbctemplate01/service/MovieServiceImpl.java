package springboot.samples.jdbctemplate01.service;

import org.springframework.stereotype.Service;
import springboot.samples.jdbctemplate01.entity.Movie;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public Movie getMovie(Long movieId) {
        return null;
    }

    @Override
    public void deleteMovie(Integer id) {

    }

    @Override
    public void addMovie(Movie movie) {

    }
}
