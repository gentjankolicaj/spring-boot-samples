package springboot.samples.different.zookeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;


@Slf4j
public class ZookeeperSSMListener extends StateMachineListenerAdapter<ZookeeperSSMState, ZookeeperSSMEvent> {


    @Override
    public void stateChanged(State<ZookeeperSSMState, ZookeeperSSMEvent> from,
                             State<ZookeeperSSMState, ZookeeperSSMEvent> to) {
        ZookeeperSSMState fromState = from != null ? from.getId() : null;
        ZookeeperSSMState toState = to != null ? to.getId() : null;
        log.info("Distributed SSM stateChanged : [from: {}, to:{}]", fromState, toState);
    }

}
