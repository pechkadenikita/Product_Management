package com.nimapinfotech.product_management.repository;

import java.util.List;

import com.nimapinfotech.product_management.entities.Product;

public interface ProductDao {

    public boolean saveProduct(Product product);

    public Product getProductById(Long id);

    public List<Product> getAllProducts(int page, int size);

    public boolean updateProduct(Product product);

    public boolean deleteProduct(Long id);
}
