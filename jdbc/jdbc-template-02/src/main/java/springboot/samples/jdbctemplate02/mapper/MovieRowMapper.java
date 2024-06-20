package springboot.samples.jdbctemplate02.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;
import springboot.samples.jdbctemplate02.domain.Movie;

public class MovieRowMapper implements RowMapper<Movie> {

  @Override
  public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Movie(rs.getLong("id"), rs.getString("name"),
        LocalDate.parse(rs.getString("release_date")));
  }
}
