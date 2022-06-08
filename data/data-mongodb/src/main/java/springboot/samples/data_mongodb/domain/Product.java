package springboot.samples.data_mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {

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
