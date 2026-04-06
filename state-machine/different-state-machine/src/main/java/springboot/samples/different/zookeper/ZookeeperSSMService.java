package springboot.samples.different.zookeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ZookeeperSSMService {


    private final StateMachine<ZookeeperSSMState, ZookeeperSSMEvent> stateMachine;


    @Autowired
    public ZookeeperSSMService(
            @Qualifier("zookeeperSSM") StateMachine<ZookeeperSSMState, ZookeeperSSMEvent> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
        log.info("zookeeperSSM started...");
        log.info("zookeeperSSM start state: {}", stateMachine.getState());
    }

    public void event(ZookeeperSSMEvent event) {
        stateMachine.sendEvent(event);
        log.info("Zookeeper SSM state:{}", stateMachine.getState());
    }

}
