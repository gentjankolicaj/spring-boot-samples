package springboot.samples.jdbctemplate02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.jdbctemplate02.service.ActorService;

@RestController
@RequestMapping(ActorController.ACTOR_URI)
public class ActorController {

  static final String ACTOR_URI = "api/v1/actor";

  private final ActorService actorService;

  public ActorController(ActorService actorService) {
    this.actorService = actorService;
  }
}
