package springboot.samples.data_mongodb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springboot.samples.data_mongodb.domain.Product;
import springboot.samples.data_mongodb.repository.ProductRepository;

@SpringBootApplication
@Slf4j
public class Application {

  @Autowired
  ProductRepository productRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner run() {
    return args -> {
      List<Product> products = new ArrayList<>();
      for (long i = 0; i < 200; i++) {
        products.add(
            new Product(i, Long.valueOf(i).intValue(), "name-" + i, "description-" + i, 20.22, 20.0,
                i + 13, LocalDateTime.now(), LocalDateTime.now()));
      }
      log.info("Inserting {} " + products);
      productRepository.saveAll(products);
    };
  }


}
