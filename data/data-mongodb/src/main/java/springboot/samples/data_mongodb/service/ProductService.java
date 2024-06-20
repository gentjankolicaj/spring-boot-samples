package springboot.samples.data_mongodb.service;

import java.util.List;
import springboot.samples.data_mongodb.domain.Product;

public interface ProductService {

  List<Product> getAllProducts();

  Product getProductById(Long productId);

  Product saveProduct(Product product);

  void deleteProductById(Long productId);
}
