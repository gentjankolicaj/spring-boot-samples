package springboot.samples.different.cdplayer;


import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Also pay attention that we annotated CDPlayer with @WithStateMachine which instructs state
 * machine to find methods from your pojo which are then called with various transitions.
 */
@WithStateMachine(name = "CDPlayerMachine")
public class CDPlayer {

    private String name;

    //In example, we use @OnTransition annotation to hook a callback when transition happens with a target state BUSY.
    @OnTransition(target = "BUSY")
    public void busy(ExtendedState extendedState) {
        Object cd = extendedState.getVariables().get(Variables.CD);
        if (cd != null) {
            name = ((CD) cd).getName();
        }
    }

    //@OnTransition we used above can only be used with strings which are matched from enums.
    //@StatesOnTransition is then something what user can create into his own application to get a type safe annotation where a real enums can be used.
    //Let’s see an example how this state machine actually works.
    @StatesOnTransition(target = {States.CLOSED, States.IDLE})
    public void closed(ExtendedState extendedState) {
        Object cd = extendedState.getVariables().get(Variables.CD);
        if (cd != null) {
            name = ((CD) cd).getName();
        } else {
            name = "No CD";
        }
    }

}
