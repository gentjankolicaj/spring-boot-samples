package springboot.samples.kafka02_producer.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class Coordinate implements Serializable {

    private final Double lat;
    private final Double lon;



}
