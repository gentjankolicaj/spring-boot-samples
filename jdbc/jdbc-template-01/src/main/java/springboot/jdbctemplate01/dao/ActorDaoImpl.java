package springboot.jdbctemplate01.dao;

import springboot.jdbctemplate01.entity.Actor;

import java.util.List;
import java.util.Optional;

public class ActorDaoImpl implements ActorDao {
    @Override
    public List<Actor> selectActors() {
        return null;
    }

    @Override
    public Optional<Actor> selectActorById(Long actorId) {
        return Optional.empty();
    }

    @Override
    public int insertActor(Actor actor) {
        return 0;
    }

    @Override
    public int updateActor(Long actorId, Actor actor) {
        return 0;
    }

    @Override
    public int deleteActor(Long actorId) {
        return 0;
    }
}
