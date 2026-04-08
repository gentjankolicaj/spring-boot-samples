package springboot.samples.different.ordershipping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;

import java.util.List;

/**
 *
 * @author gentjan kolicaj
 * @since 4/8/26 9:30 AM
 *
 */
@Slf4j
@Configuration
public class OrderShippingConfig {


    @Bean("orderSSM")
    public StateMachine<OrderShippingState, OrderShippingEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<OrderShippingState, OrderShippingEvent> builder = new StateMachineBuilder.Builder<>();

        //configure states
        builder.configureStates()
                //Root states
                .withStates()
                .initial(OrderShippingState.WAIT_NEW_ORDER)
                .state(OrderShippingState.RECEIVE_ORDER)
                .choice(OrderShippingState.CHOICE_HANDLE_ORDER)
                .fork(OrderShippingState.FORK)
                .state(OrderShippingState.HANDLE_ORDER)
                .join(OrderShippingState.JOIN)
                .state(OrderShippingState.SHIP_ORDER)
                .end(OrderShippingState.CUSTOMER_ERROR)
                .end(OrderShippingState.ORDER_SHIPPED)

                //Region 1: Stock & production
                .and()
                .withStates()
                .parent(OrderShippingState.HANDLE_ORDER)
                .initial(OrderShippingState.CHECK_STOCK)
                .choice(OrderShippingState.CHOICE_PRODUCTION)
                .state(OrderShippingState.MAKE_PRODUCTION_PLAN)
                .state(OrderShippingState.PRODUCE)
                .state(OrderShippingState.FILL_ORDER)
                .state(OrderShippingState.WAIT_PRODUCT)
                .junction(OrderShippingState.JUNCTION_ORDER)
                .junction(OrderShippingState.JUNCTION_STOCK)

                //Region 2: Payment
                .and()
                .withStates()
                .parent(OrderShippingState.HANDLE_ORDER)
                .initial(OrderShippingState.SEND_BILL)
                .state(OrderShippingState.WAIT_PAYMENT)
                .state(OrderShippingState.SEND_REMINDER)
                .state(OrderShippingState.HANDLE_PAYMENT)
                .choice(OrderShippingState.CHOICE_PAYMENT_OK)
                .state(OrderShippingState.NOTIFY_CUSTOMER)
                .state(OrderShippingState.WAIT_ORDER);

        //configure transition
        builder.configureTransitions()
                //Root transitions
                .withExternal().source(OrderShippingState.WAIT_NEW_ORDER).target(OrderShippingState.RECEIVE_ORDER)
                .event(OrderShippingEvent.PLACE_ORDER)
                .action(receiveOrderAction())
                .and()
                .withExternal().source(OrderShippingState.RECEIVE_ORDER).target(OrderShippingState.CHOICE_HANDLE_ORDER)
                .and()
                .withChoice()
                .source(OrderShippingState.CHOICE_HANDLE_ORDER)
                .first(OrderShippingState.FORK, orderOk())
                .last(OrderShippingState.CUSTOMER_ERROR)
                .and()
                .withFork()
                .source(OrderShippingState.FORK)
                .target(OrderShippingState.HANDLE_ORDER)

                //Stock & product transitions
                .and()
                .withExternal().source(OrderShippingState.CHECK_STOCK).target(OrderShippingState.CHOICE_PRODUCTION)
                .and()
                .withChoice()
                .source(OrderShippingState.CHOICE_PRODUCTION)
                .first(OrderShippingState.MAKE_PRODUCTION_PLAN, makeProdPlan())
                .last(OrderShippingState.JUNCTION_STOCK)
                .and()
                .withExternal().source(OrderShippingState.MAKE_PRODUCTION_PLAN).target(OrderShippingState.JUNCTION_STOCK)
                .and()
                .withJunction()
                .source(OrderShippingState.JUNCTION_STOCK)
                .first(OrderShippingState.PRODUCE, produce())
                .last(OrderShippingState.JUNCTION_ORDER)
                .and()
                .withJunction().source(OrderShippingState.JUNCTION_ORDER).last(OrderShippingState.FILL_ORDER)
                .and()
                .withExternal().source(OrderShippingState.FILL_ORDER).target(OrderShippingState.WAIT_PRODUCT)
                .and()
                .withExternal().source(OrderShippingState.WAIT_PRODUCT).target(OrderShippingState.JOIN)

                //Payment transitions
                .and()
                .withExternal().source(OrderShippingState.SEND_BILL).target(OrderShippingState.WAIT_PAYMENT)
                .and()
                .withExternal().source(OrderShippingState.WAIT_PAYMENT).target(OrderShippingState.SEND_REMINDER)
                .and()
                .withExternal().source(OrderShippingState.WAIT_PAYMENT).target(OrderShippingState.HANDLE_PAYMENT)
                .and()
                .withExternal().source(OrderShippingState.SEND_REMINDER).target(OrderShippingState.WAIT_PAYMENT)
                .and()
                .withExternal().source(OrderShippingState.HANDLE_PAYMENT).target(OrderShippingState.CHOICE_PAYMENT_OK)
                .and()
                .withChoice()
                .source(OrderShippingState.CHOICE_PAYMENT_OK)
                .first(OrderShippingState.WAIT_ORDER, paymentOk())
                .last(OrderShippingState.NOTIFY_CUSTOMER)
                .and()
                .withExternal().source(OrderShippingState.NOTIFY_CUSTOMER).target(OrderShippingState.SEND_BILL)
                .and()
                .withExternal().source(OrderShippingState.WAIT_ORDER).target(OrderShippingState.JOIN)

                //Final assembly
                .and()
                .withJoin()
                .sources(List.of(OrderShippingState.WAIT_PRODUCT, OrderShippingState.WAIT_ORDER))
                .target(OrderShippingState.JOIN)
                .and()
                .withExternal().source(OrderShippingState.SHIP_ORDER).target(OrderShippingState.ORDER_SHIPPED);

        //configure configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        return builder.build();
    }

    Action<OrderShippingState, OrderShippingEvent> receiveOrderAction() {
        return stateContext -> {

        };
    }

    Guard<OrderShippingState, OrderShippingEvent> orderOk() {
        return ctx -> {
            log.info("Order ok: ");
            return true;
        };
    }

    Guard<OrderShippingState, OrderShippingEvent> makeProdPlan() {
        return ctx -> {
            log.info("Make prod plan: ");
            return true;
        };
    }

    Guard<OrderShippingState, OrderShippingEvent> produce() {
        return ctx -> {
            log.info("Produce: ");
            return true;
        };
    }

    Guard<OrderShippingState, OrderShippingEvent> paymentOk() {
        return ctx -> {
            log.info("Payment ok: ");
            return true;
        };
    }


}
