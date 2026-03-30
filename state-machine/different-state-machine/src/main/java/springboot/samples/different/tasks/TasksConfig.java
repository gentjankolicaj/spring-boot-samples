package springboot.samples.different.tasks;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class TasksConfig {


  @Bean("tasksSSM")
  public StateMachine<TasksState, TasksEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<TasksState, TasksEvent> builder = StateMachineBuilder.builder();

    //configure states

    //configure transitions

    //configure configurations

    return builder.build();
  }

}
