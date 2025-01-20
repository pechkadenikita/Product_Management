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

import com.nimapinfotech.product_management.entities.Category;
import com.nimapinfotech.product_management.exception.CategoryCannotBeNullException;
import com.nimapinfotech.product_management.exception.CategoryNotFoundException;
import com.nimapinfotech.product_management.exception.DuplicateCategoryException;
import com.nimapinfotech.product_management.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        try {
            boolean isSaved = categoryService.saveCategory(category);
            return isSaved ? ResponseEntity.ok("Category saved successfully!")
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save category");
        } catch (CategoryCannotBeNullException | DuplicateCategoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(categoryService.getAllCategories(page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody Category category) {
        try {
            boolean isUpdated = categoryService.updateCategory(category);
            return isUpdated ? ResponseEntity.ok("Category updated successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update category");
        } catch (CategoryCannotBeNullException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            boolean isDeleted = categoryService.deleteCategory(id);
            return isDeleted ? ResponseEntity.ok("Category deleted successfully!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete category");
        } catch (CategoryCannotBeNullException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}