package springboot.samples.different.ordershipping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gentjan kolicaj
 * @since 4/8/26 11:03 AM
 *
 */
@RestController
@RequestMapping("/statemachine/ordershipping")
@RequiredArgsConstructor
public class OrderShippingController {

    private final OrderShippingService orderShippingService;

    @PostMapping
    public void event(@RequestBody OrderShippingEvent orderShippingEvent) {
        this.orderShippingService.event(orderShippingEvent);
    }

}
