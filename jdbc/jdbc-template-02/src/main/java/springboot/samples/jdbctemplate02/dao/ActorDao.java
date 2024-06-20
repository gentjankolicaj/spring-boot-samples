package springboot.samples.jdbctemplate02.dao;

import java.util.List;
import java.util.Optional;
import springboot.samples.jdbctemplate02.domain.Actor;

public interface ActorDao {

  List<Actor> selectActors();

  Optional<Actor> selectActorById(Long actorId);

  int insertActor(Actor actor);

  int updateActor(Long actorId, Actor actor);

  int deleteActor(Long actorId);
}
