package springboot.samples.jdbctemplate02.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;
import springboot.samples.jdbctemplate02.domain.Actor;

public class ActorRowMapper implements RowMapper<Actor> {

  @Override
  public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Actor(rs.getLong("id"), rs.getString("name"), rs.getString("surname"),
        LocalDate.parse(rs.getString("birthday")), rs.getString("birthplace"));
  }
}
