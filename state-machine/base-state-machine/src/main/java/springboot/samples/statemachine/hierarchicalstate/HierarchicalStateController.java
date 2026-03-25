package springboot.samples.statemachine.hierarchicalstate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gentjan kolicaj
 * @since 3/25/26 10:49 AM
 *
 */
@RestController
@RequestMapping("/statemachine/hierarchicalstate")
public class HierarchicalStateController {

    private final HierarchicalStateService service;

    public HierarchicalStateController(HierarchicalStateService service) {
        this.service = service;
    }

    @PostMapping
    public void input(@RequestBody String input) {
        service.sendEvent(input);
    }
}
