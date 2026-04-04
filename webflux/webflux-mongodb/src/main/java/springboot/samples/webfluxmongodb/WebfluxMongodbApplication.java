package springboot.samples.webfluxmongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@SpringBootApplication
public class WebfluxMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxMongodbApplication.class, args);
    }


    @Component
    @RequiredArgsConstructor
    class DataInitializer {
        private final ReservationRepository repository;

        @EventListener(ApplicationReadyEvent.class)
        public void initialize() {
            Flux<String> strings = Flux.just("Hello", "World", "This is webflux-mongodb", "Jane", "Doe", "John", "Doe");
            Flux<Reservation> reservations = strings.map(e -> new Reservation(null, e, UUID.randomUUID().toString()));

            //To save in mongo in future
            Flux<Reservation> savedFlux = reservations.flatMap(repository::save); //till this moment there is no data on db.

            repository.deleteAll() //in future delete all data
                    .log()
                    .thenMany(savedFlux) // in future save flux of data
                    .log()
                    .thenMany(repository.findAll()) //in future read all
                    .subscribe(e -> log.info("MongoDB reservation element :{}", e));

        }
    }

}
