package springboot.samples.kafka02_producer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Location implements Serializable {
    private final Long userId;
    private  final Coordinate coordinate;
    private final LocalDateTime dateTime;
}
