package springboot.samples.different.washer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/washer")
public class WasherController {

  private final WasherService washerService;

  @Autowired
  public WasherController(WasherService washerService) {
    this.washerService = washerService;
  }

  @PostMapping
  public void event(@RequestBody WasherEvent washerEvent) {
    washerService.event(washerEvent);
  }

}
