package springboot.samples.kafka02_consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Location {
    private final Long userId;
    private  final Coordinate coordinate;
    private final LocalDateTime dateTime;
}
