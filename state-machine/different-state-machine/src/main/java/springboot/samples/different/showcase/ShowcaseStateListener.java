package springboot.samples.different.showcase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;


@Slf4j
public class ShowcaseStateListener extends
        StateMachineListenerAdapter<ShowcaseState, ShowcaseEvent> {


    @Override
    public void stateChanged(State<ShowcaseState, ShowcaseEvent> from,
                             State<ShowcaseState, ShowcaseEvent> to) {
        ShowcaseState fromState = from != null ? from.getId() : null;
        ShowcaseState toState = to != null ? to.getId() : null;
        log.info("Showcase SSM stateChanged : [from: {}, to:{}]", fromState, toState);
    }

}
