package springboot.samples.jdbctemplate01.service;

import org.springframework.stereotype.Service;
import springboot.samples.jdbctemplate01.dao.ActorDao;

@Service
public class ActorServiceImpl implements ActorService {

  private final ActorDao actorDao;

  public ActorServiceImpl(ActorDao actorDao) {
    this.actorDao = actorDao;
  }
}
