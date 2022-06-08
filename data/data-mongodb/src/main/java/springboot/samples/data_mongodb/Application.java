package springboot.samples.data_mongodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.samples.data_mongodb.domain.Product;
import springboot.samples.data_mongodb.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ProductRepository productRepository;

	CommandLineRunner run(){
	return args-> {
		List<Product> products = new ArrayList<>();
		for (long i = 0; i < 200; i++) {
			products.add(new Product(i,Long.valueOf(i).intValue(),"name-"+i,"description-"+i,20.22,20.0,i+13,LocalDateTime.now(),LocalDateTime.now()));
		}
		log.info("Inserting {} "+products);
		productRepository.saveAll(products);
	};
	}


}
