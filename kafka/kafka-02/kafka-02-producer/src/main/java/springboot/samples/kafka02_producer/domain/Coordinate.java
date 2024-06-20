package springboot.samples.kafka02_producer.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Coordinate implements Serializable {

  private final Double lat;
  private final Double lon;


}
