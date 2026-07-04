package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.techstore.model.CartItem;
import com.techstore.dao.CartDAO;
import com.techstore.model.Cart;
import com.techstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private Connection connection;

    public CartDAOImpl() {

        connection = DBConnection.getConnection();

    }

    @Override
    public boolean addToCart(Cart cart) {

        String sql = "INSERT INTO cart(user_id, product_id, quantity) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getProductId());
            ps.setInt(3, cart.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    @Override
    public List<Cart> getCartByUserId(int userId) {

        List<Cart> cartList = new ArrayList<>();

        String sql = "SELECT * FROM cart WHERE user_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Cart cart = new Cart();

                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setCreatedAt(rs.getTimestamp("created_at"));

                cartList.add(cart);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return cartList;

    }

    @Override
    public boolean updateQuantity(int cartId, int quantity) {

        String sql = "UPDATE cart SET quantity=? WHERE cart_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    @Override
    public boolean removeFromCart(int cartId) {

        String sql = "DELETE FROM cart WHERE cart_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, cartId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    @Override
    public boolean clearCart(int userId) {

        String sql = "DELETE FROM cart WHERE user_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    @Override
    public Cart getCartItem(int userId, int productId) {

        String sql = "SELECT * FROM cart WHERE user_id=? AND product_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Cart cart = new Cart();

                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setCreatedAt(rs.getTimestamp("created_at"));

                return cart;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    @Override
    public List<CartItem> getCartItems(int userId) {

        List<CartItem> cartItems = new ArrayList<>();

        String sql =
                "SELECT " +
                "c.cart_id, " +
                "c.user_id, " +
                "c.product_id, " +
                "c.quantity, " +
                "p.product_name, " +
                "p.brand, " +
                "p.price, " +
                "p.image_url " +
                "FROM cart c " +
                "JOIN products p " +
                "ON c.product_id = p.product_id " +
                "WHERE c.user_id=?";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(
                        rs.getInt("cart_id"));

                item.setUserId(
                        rs.getInt("user_id"));

                item.setProductId(
                        rs.getInt("product_id"));

                item.setQuantity(
                        rs.getInt("quantity"));

                item.setProductName(
                        rs.getString("product_name"));

                item.setBrand(
                        rs.getString("brand"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setImageUrl(
                        rs.getString("image_url"));

                cartItems.add(item);

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        return cartItems;

    }

}