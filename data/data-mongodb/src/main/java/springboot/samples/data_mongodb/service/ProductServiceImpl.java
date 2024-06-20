package springboot.samples.data_mongodb.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.samples.data_mongodb.domain.Product;
import springboot.samples.data_mongodb.exception.ProductNotFoundException;
import springboot.samples.data_mongodb.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isEmpty()) {
      throw new ProductNotFoundException(String.format("Product with id %s not found.", productId));
    }
    return optionalProduct.get();
  }

  @Override
  @Transactional
  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void deleteProductById(Long productId) {
    productRepository.deleteById(productId);
  }
}
