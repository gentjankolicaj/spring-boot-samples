package springboot.samples.jdbctemplate01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.jdbctemplate01.service.ActorService;

import static springboot.samples.jdbctemplate01.controller.ActorController.ACTOR_URI;

@RestController
@RequestMapping(ACTOR_URI)
public class ActorController {
    static final String ACTOR_URI = "api/v1/actor";

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
}
