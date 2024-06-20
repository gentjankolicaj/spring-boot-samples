package springboot.samples.jdbctemplate01.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate01.domain.Movie;
import springboot.samples.jdbctemplate01.mapper.MovieRowMapper;

@Repository
public class MovieDaoImpl implements MovieDao {

  private final JdbcTemplate jdbcTemplate;
  private final MovieRowMapper movieRowMapper;

  private final String SELECT_ALL = "SELECT id, name, release_date FROM movie LIMIT 1000;";
  private final String SELECT_BY_ID = "SELECT id, name, release_date FROM movie WHERE id=?;";
  private final String INSERT = "INSERT INTO movie(id,name,release_date) VALUES (?,?,?);";
  private final String UPDATE = "UPDATE movie SET name=?, release_date WHERE id=?;";
  private final String DELETE = "DELETE FROM movie WHERE id=?;";

  @Autowired
  public MovieDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.movieRowMapper = new MovieRowMapper();
  }

  @Override
  public List<Movie> selectMovies() {
    return jdbcTemplate.query(SELECT_ALL, movieRowMapper);
  }

  @Override
  public Optional<Movie> selectMovieById(Long movieId) {
    return jdbcTemplate.query(SELECT_BY_ID, movieRowMapper, movieId).stream().findFirst();
  }

  @Override
  public int insertMovie(Movie movie) {
    return jdbcTemplate.update(INSERT, movie.getId(), movie.getName(), movie.getReleaseDate());
  }

  @Override
  public int updateMovie(Long movieId, Movie movie) {
    return jdbcTemplate.update(UPDATE, movie.getName(), movie.getReleaseDate(), movieId);
  }

  @Override
  public int deleteMovie(Long movieId) {
    return jdbcTemplate.update(DELETE, movieId);
  }
}
