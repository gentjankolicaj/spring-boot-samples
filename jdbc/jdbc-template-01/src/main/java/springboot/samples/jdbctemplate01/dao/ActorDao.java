package springboot.samples.jdbctemplate01.dao;

import springboot.samples.jdbctemplate01.domain.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {

    List<Actor> selectActors();

    Optional<Actor> selectActorById(Long actorId);

    int insertActor(Actor actor);

    int updateActor(Long actorId, Actor actor);

    int deleteActor(Long actorId);
}
