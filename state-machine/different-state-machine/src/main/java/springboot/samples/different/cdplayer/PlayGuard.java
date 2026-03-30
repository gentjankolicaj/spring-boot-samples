package springboot.samples.different.cdplayer;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class PlayGuard implements Guard<States, Events> {

  @Override
  public boolean evaluate(StateContext<States, Events> context) {
    ExtendedState extendedState = context.getExtendedState();
    return extendedState.getVariables().get(Variables.CD) != null;
  }

}