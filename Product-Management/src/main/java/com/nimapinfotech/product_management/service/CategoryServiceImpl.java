package com.nimapinfotech.product_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimapinfotech.product_management.entities.Category;
import com.nimapinfotech.product_management.exception.CategoryCannotBeNullException;
import com.nimapinfotech.product_management.exception.CategoryNotFoundException;
import com.nimapinfotech.product_management.exception.DuplicateCategoryException;
import com.nimapinfotech.product_management.repository.CategoryDao;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryDao categoryDao;

 
    public boolean saveCategory(Category category) {
    
        if (category == null) {
            throw new CategoryCannotBeNullException("Category cannot be null");
        }

        if (category.getId() != null ) {
            throw new DuplicateCategoryException("Category with ID '" + category.getId() + "' already exists.");
        }

        // Save the category if it's valid
        categoryDao.saveCategory(category);
        return true;
    }

  
    public List<Category> getAllCategories(int page, int size) {
        try {
            return categoryDao.getAllCategories(page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Category getCategoryById(Long id) {
        try {
            Category category = categoryDao.getCategoryById(id);
            if (category == null) {
                throw new CategoryNotFoundException("Category with ID " + id + " not found.");
            }
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    
    public boolean updateCategory(Category category) {
        try {
            if (category == null || category.getId() == null) {
                throw new CategoryCannotBeNullException("Category and ID cannot be null");
            }

            categoryDao.updateCategory(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
  
    public boolean deleteCategory(Long id) {
        try {
            if (id == null) {
                throw new CategoryCannotBeNullException("Category ID cannot be null");
            }

            categoryDao.deleteCategory(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
