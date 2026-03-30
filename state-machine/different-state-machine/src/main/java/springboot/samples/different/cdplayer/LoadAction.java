package springboot.samples.different.cdplayer;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class LoadAction implements Action<States, Events> {

  @Override
  public void execute(StateContext<States, Events> context) {
    Object cd = context.getMessageHeader(Variables.CD);
    context.getExtendedState().getVariables().put(Variables.CD, cd);
  }

}