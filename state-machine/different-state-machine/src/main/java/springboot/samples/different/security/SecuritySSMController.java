package springboot.samples.different.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/security")
public class SecuritySSMController {

    private final SecuritySSMService securitySSMService;

    @Autowired
    public SecuritySSMController(SecuritySSMService securitySSMService) {
        this.securitySSMService = securitySSMService;
    }

    @PostMapping
    public void event(@RequestBody SecuritySSMEvent event) {
        this.securitySSMService.event(event);
    }

}
