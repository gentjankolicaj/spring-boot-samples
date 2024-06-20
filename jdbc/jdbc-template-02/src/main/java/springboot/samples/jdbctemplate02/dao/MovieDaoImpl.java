package springboot.samples.jdbctemplate02.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate02.domain.Movie;
import springboot.samples.jdbctemplate02.mapper.MovieRowMapper;

@Repository
public class MovieDaoImpl implements MovieDao {

  private final JdbcTemplate postgresJdbcTemplate;
  private final JdbcTemplate mysqlJdbcTemplate;
  private final MovieRowMapper movieRowMapper;

  private final String SELECT_ALL = "SELECT id, name, release_date FROM movie LIMIT 1000;";
  private final String SELECT_BY_ID = "SELECT id, name, release_date FROM movie WHERE id=?;";
  private final String INSERT = "INSERT INTO movie(id,name,release_date) VALUES (?,?,?);";
  private final String UPDATE = "UPDATE movie SET name=?, release_date WHERE id=?;";
  private final String DELETE = "DELETE FROM movie WHERE id=?;";

  @Autowired
  public MovieDaoImpl(@Qualifier("mysqlJdbcTemplate") JdbcTemplate mysqlJdbcTemplate,
      @Qualifier("postgresJdbcTemplate") JdbcTemplate postgresJdbcTemplate) {
    this.mysqlJdbcTemplate = mysqlJdbcTemplate;
    this.postgresJdbcTemplate = postgresJdbcTemplate;
    this.movieRowMapper = new MovieRowMapper();
  }

  @Override
  public List<Movie> selectMovies() {
    return mysqlJdbcTemplate.query(SELECT_ALL, movieRowMapper);
  }

  @Override
  public Optional<Movie> selectMovieById(Long movieId) {
    return mysqlJdbcTemplate.query(SELECT_BY_ID, movieRowMapper, movieId).stream().findFirst();
  }

  @Override
  public int insertMovie(Movie movie) {
    postgresJdbcTemplate.update(INSERT, movie.getId(), movie.getName(), movie.getReleaseDate());
    return mysqlJdbcTemplate.update(INSERT, movie.getId(), movie.getName(), movie.getReleaseDate());
  }

  @Override
  public int updateMovie(Long movieId, Movie movie) {
    postgresJdbcTemplate.update(UPDATE, movie.getName(), movie.getReleaseDate(), movieId);
    return mysqlJdbcTemplate.update(UPDATE, movie.getName(), movie.getReleaseDate(), movieId);
  }

  @Override
  public int deleteMovie(Long movieId) {
    postgresJdbcTemplate.update(DELETE, movieId);
    return mysqlJdbcTemplate.update(DELETE, movieId);
  }
}
