package springboot.samples.jdbctemplate01.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate01.domain.Actor;
import springboot.samples.jdbctemplate01.mapper.ActorRowMapper;

@Repository
public class ActorDaoImpl implements ActorDao {

  private final JdbcTemplate jdbcTemplate;
  private final ActorRowMapper actorRowMapper;
  private final String SELECT_ALL = "SELECT id, name, surname, birthday, birthplace FROM actor LIMIT 1000;";
  private final String SELECT_BY_ID = "SELECT id, name, surname, birthday, birthplace FROM actor where id=?;";
  private final String INSERT = "INSERT INTO actor(id,name,surname,birthday,birthplace) VALUES (?,?,?,?,?);";
  private final String UPDATE = "UPDATE actor SET name=?, surname=?, birthday=?, birthplace=? WHERE id=?;";
  private final String DELETE = "DELETE FROM actor WHERE id=?;";


  @Autowired
  public ActorDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.actorRowMapper = new ActorRowMapper();
  }

  @Override
  public List<Actor> selectActors() {
    return jdbcTemplate.query(SELECT_ALL, actorRowMapper);
  }

  @Override
  public Optional<Actor> selectActorById(Long actorId) {
    return jdbcTemplate.query(SELECT_BY_ID, actorRowMapper, actorId).stream().findFirst();
  }

  @Override
  public int insertActor(Actor actor) {
    return jdbcTemplate.update(INSERT, actor.getId(), actor.getName(), actor.getSurname(),
        actor.getBirthday(), actor.getBirthplace());
  }

  @Override
  public int updateActor(Long actorId, Actor actor) {
    return jdbcTemplate.update(UPDATE, actor.getName(), actor.getSurname(), actor.getBirthday(),
        actor.getBirthplace(), actorId);
  }

  @Override
  public int deleteActor(Long actorId) {
    return jdbcTemplate.update(DELETE, actorId);
  }
}
