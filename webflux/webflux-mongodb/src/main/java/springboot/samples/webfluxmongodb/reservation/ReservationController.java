package springboot.samples.webfluxmongodb.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:44 AM
 *
 */
@RestController("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @GetMapping
    public Flux<Reservation> findAll() {
        return reservationRepository.findAll();
    }


}
