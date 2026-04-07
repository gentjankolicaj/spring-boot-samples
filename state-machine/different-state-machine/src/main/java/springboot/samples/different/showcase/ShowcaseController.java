package springboot.samples.different.showcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/showcase")
public class ShowcaseController {

    private final ShowcaseService showcaseService;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    @PostMapping
    public void event(@RequestBody ShowcaseEvent showcaseEvent) {
        showcaseService.event(showcaseEvent);
    }

}
