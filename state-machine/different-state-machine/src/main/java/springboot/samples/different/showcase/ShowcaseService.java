package springboot.samples.different.showcase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ShowcaseService {


    private final StateMachine<ShowcaseState, ShowcaseEvent> stateMachine;


    @Autowired
    public ShowcaseService(
            @Qualifier("showcaseSSM") StateMachine<ShowcaseState, ShowcaseEvent> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void event(ShowcaseEvent event) {
        stateMachine.sendEvent(event);
        log.info("Showcase SSM state:{}", stateMachine.getState());
    }

}
