package springboot.samples.jdbctemplate01.dao;

import org.springframework.stereotype.Repository;
import springboot.samples.jdbctemplate01.entity.Actor;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDaoImpl implements ActorDao {
    @Override
    public List<Actor> selectActors() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<Actor> selectActorById(Long actorId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int insertActor(Actor actor) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int updateActor(Long actorId, Actor actor) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int deleteActor(Long actorId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
