package springboot.samples.different.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/persist")
public class PersistController {

  private final PersistService persistService;

  @Autowired
  public PersistController(PersistService persistService) {
    this.persistService = persistService;
  }

  @PostMapping
  public void event(@RequestBody PersistEvent persistEvent) {
    persistService.event(persistEvent);
  }

}
