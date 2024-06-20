package springboot.samples.jdbctemplate02.service;

import org.springframework.stereotype.Service;
import springboot.samples.jdbctemplate02.dao.ActorDao;

@Service
public class ActorServiceImpl implements ActorService {

  private final ActorDao actorDao;

  public ActorServiceImpl(ActorDao actorDao) {
    this.actorDao = actorDao;
  }
}
