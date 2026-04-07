package springboot.samples.different.cdplayer;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ClosedEntryAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        if (context.getTransition() != null
                && context.getEvent() == Events.PLAY
                && context.getTransition().getTarget().getId() == States.CLOSED
                && context.getExtendedState().getVariables().get(Variables.CD) != null) {
            context.getStateMachine().sendEvent(Events.PLAY);
        }
    }

}