package com.nimapinfotech.product_management.service;

import java.util.List;

import com.nimapinfotech.product_management.entities.Category;

public interface CategoryService {

	public boolean saveCategory(Category category);

	public List<Category> getAllCategories(int page, int size);

	public Category getCategoryById(Long id);

	public boolean updateCategory(Category category);

	public boolean deleteCategory(Long id);
}
