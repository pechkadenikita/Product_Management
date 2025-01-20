package com.nimapinfotech.product_management.service;

import java.util.List;

import com.nimapinfotech.product_management.entities.Product;

public interface ProductService {

    boolean saveProduct(Product product);

    List<Product> getAllProducts(int page, int size);

    Product getProductById(Long id);

    boolean updateProduct(Product product);

    boolean deleteProduct(Long id);
}
