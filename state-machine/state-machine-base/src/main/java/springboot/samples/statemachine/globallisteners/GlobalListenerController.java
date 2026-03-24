package springboot.samples.statemachine.globallisteners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:58 PM
 *
 */
@RestController
@RequestMapping("/statemachine/globallistener")
public class GlobalListenerController {

    private final GlobalListenerStateMachineService service;

    @Autowired
    public GlobalListenerController(GlobalListenerStateMachineService service) {
        this.service = service;
    }

    @PostMapping
    public void inputEvent(@RequestBody String event) {
        service.sendEvent(event);
    }

}
