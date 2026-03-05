package springboot.samples.integration.kafka;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Greet implements Serializable {

  private Long id;
  private String content;
  private LocalDateTime createdDate;


}
