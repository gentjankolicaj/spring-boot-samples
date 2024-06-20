package springboot.samples.kafka02_consumer.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

  private final Long userId;
  private final Coordinate coordinate;
  private final LocalDateTime dateTime;
}
