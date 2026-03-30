package springboot.samples.different.cdplayer;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class PlayAction implements Action<States, Events> {

  @Override
  public void execute(StateContext<States, Events> context) {
    context.getExtendedState().getVariables().put(Variables.ELAPSEDTIME, 0L);
    context.getExtendedState().getVariables().put(Variables.TRACK, 0);
  }

}