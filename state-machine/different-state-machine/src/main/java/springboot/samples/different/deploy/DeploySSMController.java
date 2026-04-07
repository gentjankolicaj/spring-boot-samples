package springboot.samples.different.deploy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/deploy")
@RequiredArgsConstructor
public class DeploySSMController {

    private final DeploySSMService deploySSMService;


    @PostMapping
    public void event(DeploySSMEvent deploySSMEvent) {
        this.deploySSMService.event(deploySSMEvent);
    }


}
