package springboot.samples.data_mongodb.service;

import springboot.samples.data_mongodb.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);

    Product saveProduct(Product product);

    void deleteProductById(Long productId);
}
