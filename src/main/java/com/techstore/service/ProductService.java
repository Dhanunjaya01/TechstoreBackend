package com.techstore.service;

import java.util.List;

import com.techstore.dao.ProductDAO;
import com.techstore.daoimpl.ProductDAOImpl;
import com.techstore.model.Product;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {

        productDAO = new ProductDAOImpl();

    }

    // Add Product
    public boolean addProduct(Product product) {

        return productDAO.addProduct(product);

    }

    // Get Product By Id
    public Product getProductById(int productId) {

        return productDAO.getProductById(productId);

    }

    // Get All Products
    public List<Product> getAllProducts() {

        return productDAO.getAllProducts();

    }

    // Update Product
    public boolean updateProduct(Product product) {

        return productDAO.updateProduct(product);

    }

    // Delete Product
    public boolean deleteProduct(int productId) {

        return productDAO.deleteProduct(productId);

    }

    // Search Product
    public List<Product> searchProducts(String keyword) {

        return productDAO.searchProducts(keyword);

    }

    // Category Filter
    public List<Product> getProductsByCategory(String category) {

        return productDAO.getProductsByCategory(category);

    }

    // Brand Filter
    public List<Product> getProductsByBrand(String brand) {

        return productDAO.getProductsByBrand(brand);

    }

}