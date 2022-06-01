package springboot.samples.jdbctemplate02.mapper;

import org.springframework.jdbc.core.RowMapper;
import springboot.samples.jdbctemplate02.entity.Actor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Actor(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), LocalDate.parse(rs.getString("birthday")), rs.getString("birthplace"));
    }
}
