package springboot.samples.jdbctemplate01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.samples.jdbctemplate01.entity.Movie;
import springboot.samples.jdbctemplate01.service.MovieService;

import java.util.List;

@RestController
@RequestMapping(MovieController.MOVIE_URI)
public class MovieController {
    static final String MOVIE_URI = "api/v1/movie";

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping()
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("{movieId}")
    public Movie getMovieId(@PathVariable("movieId") Long movieId) {
        return movieService.getMovie(movieId);
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @DeleteMapping("{movieId}")
    public void deleteMovie(@PathVariable("movieId") Integer id) {
        movieService.deleteMovie(id);
    }

}
