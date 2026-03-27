package springboot.samples.different.showcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.different.turnstile.TurnstileEvent;
import springboot.samples.different.turnstile.TurnstileService;

@RestController
@RequestMapping("/statemachine/showcase")
public class ShowcaseController {

  private final TurnstileService turnstileService;

  @Autowired
  public ShowcaseController(TurnstileService turnstileService) {
    this.turnstileService = turnstileService;
  }

  @PostMapping
  public void event(@RequestBody TurnstileEvent turnstileEvent) {
    turnstileService.event(turnstileEvent);
  }

}
