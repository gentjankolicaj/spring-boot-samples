package springboot.samples.different.turnstile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/turnstile")
public class TurnstileController {

    private final TurnstileService turnstileService;

    @Autowired
    public TurnstileController(TurnstileService turnstileService) {
        this.turnstileService = turnstileService;
    }

    @PostMapping
    public void event(@RequestBody TurnstileEvent turnstileEvent) {
        turnstileService.event(turnstileEvent);
    }

}
