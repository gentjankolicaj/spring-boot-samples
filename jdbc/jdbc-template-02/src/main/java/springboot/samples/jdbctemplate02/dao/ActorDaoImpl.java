package springboot.samples.jdbctemplate02.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate02.domain.Actor;
import springboot.samples.jdbctemplate02.mapper.ActorRowMapper;

@Repository
public class ActorDaoImpl implements ActorDao {

  private final JdbcTemplate postgresJdbcTemplate;
  private final JdbcTemplate mysqlJdbcTemplate;
  private final ActorRowMapper actorRowMapper;
  private final String SELECT_ALL = "SELECT id, name, surname, birthday, birthplace FROM actor LIMIT 1000;";
  private final String SELECT_BY_ID = "SELECT id, name, surname, birthday, birthplace FROM actor where id=?;";
  private final String INSERT = "INSERT INTO actor(id,name,surname,birthday,birthplace) VALUES (?,?,?,?,?);";
  private final String UPDATE = "UPDATE actor SET name=?, surname=?, birthday=?, birthplace=? WHERE id=?;";
  private final String DELETE = "DELETE FROM actor WHERE id=?;";


  @Autowired
  public ActorDaoImpl(@Qualifier("mysqlJdbcTemplate") JdbcTemplate mysqlJdbcTemplate,
      @Qualifier("postgresJdbcTemplate") JdbcTemplate postgresJdbcTemplate) {
    this.mysqlJdbcTemplate = mysqlJdbcTemplate;
    this.postgresJdbcTemplate = postgresJdbcTemplate;
    this.actorRowMapper = new ActorRowMapper();
  }

  @Override
  public List<Actor> selectActors() {
    return mysqlJdbcTemplate.query(SELECT_ALL, actorRowMapper);
  }

  @Override
  public Optional<Actor> selectActorById(Long actorId) {
    return mysqlJdbcTemplate.query(SELECT_BY_ID, actorRowMapper, actorId).stream().findFirst();
  }

  @Override
  public int insertActor(Actor actor) {
    postgresJdbcTemplate.update(INSERT, actor.getId(), actor.getName(), actor.getSurname(),
        actor.getBirthday(), actor.getBirthplace());
    return mysqlJdbcTemplate.update(INSERT, actor.getId(), actor.getName(), actor.getSurname(),
        actor.getBirthday(), actor.getBirthplace());
  }

  @Override
  public int updateActor(Long actorId, Actor actor) {
    postgresJdbcTemplate.update(UPDATE, actor.getName(), actor.getSurname(), actor.getBirthday(),
        actor.getBirthplace(), actorId);
    return mysqlJdbcTemplate.update(UPDATE, actor.getName(), actor.getSurname(),
        actor.getBirthday(), actor.getBirthplace(), actorId);
  }

  @Override
  public int deleteActor(Long actorId) {
    postgresJdbcTemplate.update(DELETE, actorId);
    return mysqlJdbcTemplate.update(DELETE, actorId);
  }
}
