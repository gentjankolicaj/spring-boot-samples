package springboot.samples.data_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.data_mongodb.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

}
