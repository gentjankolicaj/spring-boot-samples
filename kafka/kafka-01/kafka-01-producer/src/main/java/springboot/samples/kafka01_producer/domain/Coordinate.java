package springboot.samples.kafka01_producer.domain;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Coordinate {

  private final Double lat;
  private final Double lon;


}
