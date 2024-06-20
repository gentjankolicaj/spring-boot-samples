package springboot.samples.kafka02_producer.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location implements Serializable {

  private final Long userId;
  private final Coordinate coordinate;
  private final LocalDateTime dateTime;
}
