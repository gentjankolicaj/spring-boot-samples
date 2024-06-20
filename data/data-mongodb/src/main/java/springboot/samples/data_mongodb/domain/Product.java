package springboot.samples.data_mongodb.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

  @Id
  private Long id;
  private Integer status;
  private String name;
  private String description;
  private Double regularPrice;
  private Double discountPrice;
  private Long quantity;
  private LocalDateTime insertDate;
  private LocalDateTime updateDate;


}
