package springboot.samples.different.ordershipping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 *
 * @author gentjan kolicaj
 * @since 4/8/26 9:33 AM
 *
 */
@Slf4j
@Service
public class OrderShippingService {

    private final StateMachine<OrderShippingState, OrderShippingEvent> stateMachine;

    public OrderShippingService(@Qualifier("orderSSM") StateMachine<OrderShippingState, OrderShippingEvent> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void event(OrderShippingEvent orderShippingEvent) {
        this.stateMachine.sendEvent(orderShippingEvent);
        log.info("Order SSM: {}", this.stateMachine.getState());
    }
}
