package springboot.samples.tenisstatemachine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class TenisStateMachineService {


  private final StateMachine<TenisStateType, String> stateMachine;

  @Autowired
  public TenisStateMachineService(StateMachine<TenisStateType, String> stateMachine) {
    this.stateMachine = stateMachine;
  }

  public void processInput(String input) {
    String[] args;
    if (input == null || input.isBlank()) {
      args = new String[]{};
    } else {
      args = input.split(",");
    }
    for (String event : args) {
      stateMachine.sendEvent(event);
    }
  }

}
