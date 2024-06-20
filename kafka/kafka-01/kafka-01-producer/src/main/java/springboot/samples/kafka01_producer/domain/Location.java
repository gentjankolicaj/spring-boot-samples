package springboot.samples.kafka01_producer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

  private final Long userId;
  private final Coordinate coordinate;
}
