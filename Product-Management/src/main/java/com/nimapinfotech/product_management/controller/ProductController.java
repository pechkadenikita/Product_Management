package com.nimapinfotech.product_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimapinfotech.product_management.entities.Product;
import com.nimapinfotech.product_management.exception.ProductCannotBeNullException;
import com.nimapinfotech.product_management.exception.ProductNotFoundException;
import com.nimapinfotech.product_management.exception.DuplicateProductException;
import com.nimapinfotech.product_management.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        try {
            boolean isSaved = productService.saveProduct(product);
            return isSaved ? ResponseEntity.ok("Product saved successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save product");
        } catch (ProductCannotBeNullException | DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(productService.getAllProducts(page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            boolean isUpdated = productService.updateProduct(product);
            return isUpdated ? ResponseEntity.ok("Product updated successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update product");
        } catch (ProductCannotBeNullException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            boolean isDeleted = productService.deleteProduct(id);
            return isDeleted ? ResponseEntity.ok("Product deleted successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete product");
        } catch (ProductCannotBeNullException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
