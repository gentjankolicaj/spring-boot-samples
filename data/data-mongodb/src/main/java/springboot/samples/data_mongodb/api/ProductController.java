package springboot.samples.data_mongodb.api;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.samples.data_mongodb.domain.Product;
import springboot.samples.data_mongodb.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<Product>> getAllProducts() {
    log.info("Fetching all products.");
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
  public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
    log.info("Fetching product with id {}", productId);
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
    log.info("Saving product {}", product);
    URI uri = URI.create(
        ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/product").toUriString());
    return ResponseEntity.created(uri).body(productService.saveProduct(product));
  }

  @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
    log.info("Deleting product {}", productId);
    productService.deleteProductById(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
