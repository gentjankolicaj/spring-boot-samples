package springboot.samples.different.cdplayer;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.util.Map;

public class PlayingAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        Map<Object, Object> variables = context.getExtendedState().getVariables();
        Object elapsed = variables.get(Variables.ELAPSEDTIME);
        Object cd = variables.get(Variables.CD);
        Object track = variables.get(Variables.TRACK);
        if (elapsed instanceof Long) {
            long e = ((Long) elapsed) + 1000L;
            if (e > ((CD) cd).getTracks()[((Integer) track)].getLength() * 1000L) {
                context.getStateMachine().sendEvent(MessageBuilder
                        .withPayload(Events.FORWARD)
                        .setHeader(Headers.TRACKSHIFT.toString(), 1).build());
            } else {
                variables.put(Variables.ELAPSEDTIME, e);
            }
        }
    }

}