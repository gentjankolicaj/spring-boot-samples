package springboot.samples.different.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TasksService {

  private final StateMachine<TasksState, TasksEvent> stateMachine;

  @Autowired
  public TasksService(@Qualifier("tasksSSM") StateMachine<TasksState, TasksEvent> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }

  public void event(TasksEvent event) {
    stateMachine.sendEvent(event);
    log.info("Tasks SSM state:{}", stateMachine.getState());
  }

}
