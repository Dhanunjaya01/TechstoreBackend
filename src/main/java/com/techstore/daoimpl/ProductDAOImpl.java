package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techstore.dao.ProductDAO;
import com.techstore.model.Product;
import com.techstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addProduct(Product product) {

        String sql = "INSERT INTO products(product_name,brand,category,description,price,discount_percent,stock,rating,image_url) VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getBrand());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getDiscountPercent());
            ps.setInt(7, product.getStock());
            ps.setDouble(8, product.getRating());
            ps.setString(9, product.getImageUrl());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Product getProductById(int productId) {

        String sql = "SELECT * FROM products WHERE product_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapProduct(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(mapProduct(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return products;
    }

    @Override
    public boolean updateProduct(Product product) {

        String sql = "UPDATE products SET product_name=?,brand=?,category=?,description=?,price=?,discount_percent=?,stock=?,rating=?,image_url=? WHERE product_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getBrand());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getDiscountPercent());
            ps.setInt(7, product.getStock());
            ps.setDouble(8, product.getRating());
            ps.setString(9, product.getImageUrl());
            ps.setInt(10, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {

        String sql = "DELETE FROM products WHERE product_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, productId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE product_name LIKE ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(mapProduct(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE category=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(mapProduct(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return products;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE brand=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, brand);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(mapProduct(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return products;
    }

    private Product mapProduct(ResultSet rs) throws SQLException {

        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setBrand(rs.getString("brand"));
        product.setCategory(rs.getString("category"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setDiscountPercent(rs.getInt("discount_percent"));
        product.setStock(rs.getInt("stock"));
        product.setRating(rs.getDouble("rating"));
        product.setImageUrl(rs.getString("image_url"));

        return product;
    }

}