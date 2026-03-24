package springboot.samples.tenisstatemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 *
 * @author gentjan kolicaj
 * @since 3/24/26 4:59 PM
 *
 */
@Slf4j
public class TenisStateMachineListener extends StateMachineListenerAdapter<TenisStateType, String> {

    @Override
    public void stateChanged(State<TenisStateType, String> from, State<TenisStateType, String> to) {
        log.info("State changed:[from:{},to:{}]", from.getId(), to.getId());
    }
}
