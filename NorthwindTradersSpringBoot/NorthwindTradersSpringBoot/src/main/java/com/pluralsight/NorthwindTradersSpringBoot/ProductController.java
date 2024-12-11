package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductRepository;
import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/rest/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setSupplierID(updatedProduct.getSupplierID());
            existingProduct.setCategoryID(updatedProduct.getCategoryID());
            existingProduct.setQuantityPerUnit(updatedProduct.getQuantityPerUnit());
            existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
            existingProduct.setUnitInStock(updatedProduct.getUnitInStock());
            existingProduct.setUnitOnOrder(updatedProduct.getUnitOnOrder());
            existingProduct.setReorderLevel(updatedProduct.getReorderLevel());
            existingProduct.setDiscontinued(updatedProduct.getDiscontinued());
            Product savedProduct = productRepository.save(existingProduct);
            return ResponseEntity.ok(savedProduct);
        }).orElseGet(() -> {
            updatedProduct.setProductID(id);
            Product savedProduct = productRepository.save(updatedProduct);
            return ResponseEntity.status(201).body(savedProduct);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
