package springboot.samples.different.zookeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/zookeeper")
public class ZookeeperSSMController {

    private final ZookeeperSSMService zookeeperSSMService;

    @Autowired
    public ZookeeperSSMController(ZookeeperSSMService zookeeperSSMService) {
        this.zookeeperSSMService = zookeeperSSMService;
    }

    @PostMapping
    public void event(@RequestBody ZookeeperSSMEvent zookeeperSSMEvent) {
        zookeeperSSMService.event(zookeeperSSMEvent);
    }

}
