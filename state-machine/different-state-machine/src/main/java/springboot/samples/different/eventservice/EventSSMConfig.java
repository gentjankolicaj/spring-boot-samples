package springboot.samples.different.eventservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;

@Slf4j
@Configuration
public class EventSSMConfig {

    public static final String TOTAL_ITEMS_KEY = "totalItems";
    public static final String ITEMS_KEY = "items";

    @Bean("eventSSM")
    @Scope("prototype") // This is the crucial missing piece
    public StateMachine<EventSSMState, EventSSMEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<EventSSMState, EventSSMEvent> builder = new Builder<>();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(EventSSMState.LANDING)
                .states(new HashSet<>(EnumSet.allOf(EventSSMState.class)));

        //configure transitions
        builder.configureTransitions()
                .withExternal().source(EventSSMState.LANDING).target(EventSSMState.ITEMS)
                .event(EventSSMEvent.VIEW_I)
                .and()
                .withInternal().source(EventSSMState.ITEMS).event(EventSSMEvent.ADD).action(addAction())
                .and()
                .withExternal().source(EventSSMState.ITEMS).target(EventSSMState.CART)
                .event(EventSSMEvent.VIEW_C)
                .and()
                .withInternal().source(EventSSMState.CART).event(EventSSMEvent.DEL).action(delAction())
                .and()
                .withExternal().source(EventSSMState.CART).target(EventSSMState.PAYMENT)
                .event(EventSSMEvent.VIEW_P)
                .and()
                .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.CART)
                .event(EventSSMEvent.VIEW_C)
                .and()
                .withExternal().source(EventSSMState.CART).target(EventSSMState.LANDING)
                .event(EventSSMEvent.RESET)
                .and()
                .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.ITEMS)
                .event(EventSSMEvent.VIEW_I)
                .and()
                .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.LANDING)
                .event(EventSSMEvent.RESET);

        //configure configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        return builder.build();
    }

    Action<EventSSMState, EventSSMEvent> addAction() {
        return stateContext -> {
            Map<Object, Object> vars = stateContext.getExtendedState().getVariables();

            //increment item
            Integer totalItems = stateContext.getExtendedState().get(TOTAL_ITEMS_KEY, Integer.class);
            totalItems = (Integer) (totalItems == null ? vars.put(TOTAL_ITEMS_KEY, 1) : vars.put(TOTAL_ITEMS_KEY, totalItems + 1));
            log.info("addAction(): Variable 'totalItems' value {}", totalItems);

            //more below

        };
    }

    Action<EventSSMState, EventSSMEvent> delAction() {
        return stateContext -> {
            Map<Object, Object> vars = stateContext.getExtendedState().getVariables();

            //decrement item
            Integer totalItems = stateContext.getExtendedState().get(TOTAL_ITEMS_KEY, Integer.class);
            totalItems = (Integer) (totalItems == null ? vars.put(TOTAL_ITEMS_KEY, 0) : vars.put(TOTAL_ITEMS_KEY, totalItems - 1));

            log.info("delAction(): Variable 'totalItems' value {}", totalItems);

            //more below
        };
    }

}
