package springboot.samples.different.cdplayer;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.util.Map;

public class TrackAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        Map<Object, Object> variables = context.getExtendedState().getVariables();
        Object trackshift = context.getMessageHeader(Headers.TRACKSHIFT.toString());
        Object track = variables.get(Variables.TRACK);
        Object cd = variables.get(Variables.CD);
        if (trackshift instanceof Integer && track instanceof Integer && cd instanceof CD) {
            int next = ((Integer) track) + ((Integer) trackshift);
            if (next >= 0 && ((CD) cd).getTracks().length > next) {
                variables.put(Variables.ELAPSEDTIME, 0L);
                variables.put(Variables.TRACK, next);
            } else if (((CD) cd).getTracks().length <= next) {
                context.getStateMachine().sendEvent(Events.STOP);
            }
        }
    }

}