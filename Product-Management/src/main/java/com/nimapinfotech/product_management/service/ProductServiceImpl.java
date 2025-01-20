package com.nimapinfotech.product_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimapinfotech.product_management.entities.Product;
import com.nimapinfotech.product_management.exception.DuplicateProductException;
import com.nimapinfotech.product_management.exception.ProductCannotBeNullException;
import com.nimapinfotech.product_management.exception.ProductNotFoundException;
import com.nimapinfotech.product_management.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean saveProduct(Product product) {
        try {
            if (product == null) {
                throw new ProductCannotBeNullException("Product cannot be null");
            }

            if (product.getId() != null && productDao.getProductById(product.getId()) != null) {
                throw new DuplicateProductException("Product with ID '" + product.getId() + "' already exists.");
            }

            productDao.saveProduct(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts(int page, int size) {
        try {
            return productDao.getAllProducts(page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            Product product = productDao.getProductById(id);
            if (product == null) {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            if (product == null || product.getId() == null) {
                throw new ProductCannotBeNullException("Product and ID cannot be null");
            }

            productDao.updateProduct(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        try {
            if (id == null) {
                throw new ProductCannotBeNullException("Product ID cannot be null");
            }

            productDao.deleteProduct(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
