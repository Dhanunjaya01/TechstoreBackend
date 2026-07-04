package com.techstore.dao;

import java.util.List;

import com.techstore.model.Product;

public interface ProductDAO {

    // Add Product
    boolean addProduct(Product product);

    // Get Product by ID
    Product getProductById(int productId);

    // Get All Products
    List<Product> getAllProducts();

    // Update Product
    boolean updateProduct(Product product);

    // Delete Product
    boolean deleteProduct(int productId);

    // Search Products
    List<Product> searchProducts(String keyword);

    // Get Products by Category
    List<Product> getProductsByCategory(String category);

    // Get Products by Brand
    List<Product> getProductsByBrand(String brand);

}