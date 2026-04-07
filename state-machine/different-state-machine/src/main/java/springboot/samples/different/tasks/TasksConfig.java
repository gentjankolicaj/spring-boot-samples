package springboot.samples.different.tasks;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Configuration
public class TasksConfig {


    @Bean("tasksSSM")
    public StateMachine<TasksState, TasksEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<TasksState, TasksEvent> builder = StateMachineBuilder.builder();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(TasksState.READY)
                .state(TasksState.FORK)
                .fork(TasksState.TASKS)
                .join(TasksState.JOIN)
                .choice(TasksState.CHOICE)
                .and()
                .withStates()
                .parent(TasksState.TASKS)
                .initial(TasksState.T1)
                .and()
                .withStates()
                .parent(TasksState.TASKS)
                .initial(TasksState.T2)
                .and()
                .withStates()
                .parent(TasksState.TASKS)
                .initial(TasksState.T3)
                .and()
                .withStates()
                .parent(TasksState.ERROR)
                .initial(TasksState.AUTOMATIC)
                .state(TasksState.AUTOMATIC, automaticAction(), null)
                .state(TasksState.MANUAL);

        //configure transitions
        builder.configureTransitions()
                .withExternal().source(TasksState.READY).target(TasksState.FORK).event(TasksEvent.RUN)
                .and()
                .withExternal().source(TasksState.FORK).target(TasksState.TASKS).event(TasksEvent.FORK)
                .and()
                .withFork()
                .source(TasksState.TASKS)
                .target(TasksState.T1)
                .target(TasksState.T2)
                .target(TasksState.T3)
                .and()
                .withExternal().source(TasksState.T1).target(TasksState.T1E)
                .and()
                .withExternal()
                .source(TasksState.T2).target(TasksState.T2E)
                .and()
                .withExternal()
                .source(TasksState.T3).target(TasksState.T3E)
                .and()
                .withJoin()
                .source(TasksState.TASKS).target(TasksState.JOIN)
                .and()
                .withExternal()
                .source(TasksState.JOIN).target(TasksState.CHOICE)
                .and()
                .withChoice()
                .source(TasksState.CHOICE)
                .first(TasksState.ERROR, trueChoiceGuard())
                .last(TasksState.READY)
                .and()
                .withExternal()
                .source(TasksState.ERROR).target(TasksState.READY).event(TasksEvent.CONTINUE)
                .and()
                .withExternal()
                .source(TasksState.AUTOMATIC).target(TasksState.MANUAL).event(TasksEvent.FALLBACK)
                .and()
                .withInternal()
                .source(TasksState.MANUAL).action(fixAction()).event(TasksEvent.FIX);

        //configure configurations
        return builder.build();
    }


    @Bean
    public Guard<TasksState, TasksEvent> trueChoiceGuard() {
        return new Guard<TasksState, TasksEvent>() {

            @Override
            public boolean evaluate(StateContext<TasksState, TasksEvent> context) {
                Map<Object, Object> variables = context.getExtendedState().getVariables();
                return !(ObjectUtils.nullSafeEquals(variables.get("T1"), true)
                        && ObjectUtils.nullSafeEquals(variables.get("T2"), true)
                        && ObjectUtils.nullSafeEquals(variables.get("T3"), true));
            }
        };
    }

    @Bean
    public Action<TasksState, TasksEvent> automaticAction() {
        return new Action<TasksState, TasksEvent>() {

            @Override
            public void execute(StateContext<TasksState, TasksEvent> context) {
                Map<Object, Object> variables = context.getExtendedState().getVariables();
                if (ObjectUtils.nullSafeEquals(variables.get("T1"), true)
                        && ObjectUtils.nullSafeEquals(variables.get("T2"), true)
                        && ObjectUtils.nullSafeEquals(variables.get("T3"), true)) {
                    context.getStateMachine().sendEvent(TasksEvent.CONTINUE);
                } else {
                    context.getStateMachine().sendEvent(TasksEvent.FALLBACK);
                }
            }
        };
    }

    @Bean
    public Action<TasksState, TasksEvent> fixAction() {
        return new Action<TasksState, TasksEvent>() {

            @Override
            public void execute(StateContext<TasksState, TasksEvent> context) {
                Map<Object, Object> variables = context.getExtendedState().getVariables();
                variables.put("T1", true);
                variables.put("T2", true);
                variables.put("T3", true);
                context.getStateMachine().sendEvent(TasksEvent.CONTINUE);
            }
        };
    }

}
