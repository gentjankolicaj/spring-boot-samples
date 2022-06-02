package springboot.samples.jdbctemplate01.mapper;

import org.springframework.jdbc.core.RowMapper;
import springboot.samples.jdbctemplate01.domain.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Movie(rs.getLong("id"), rs.getString("name"), rs.getString("release_date")!=null ? LocalDate.parse(rs.getString("release_date")) : null);
    }
}
