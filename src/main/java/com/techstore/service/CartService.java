package com.techstore.service;

import java.util.List;
import com.techstore.model.CartItem;
import com.techstore.dao.CartDAO;
import com.techstore.daoimpl.CartDAOImpl;
import com.techstore.model.Cart;

public class CartService {

    private CartDAO cartDAO;

    public CartService() {

        cartDAO = new CartDAOImpl();

    }

    public boolean addToCart(Cart cart) {

        return cartDAO.addToCart(cart);

    }

    public List<Cart> getCartByUserId(int userId) {

        return cartDAO.getCartByUserId(userId);

    }

    public boolean updateQuantity(int cartId, int quantity) {

        return cartDAO.updateQuantity(cartId, quantity);

    }

    public boolean removeFromCart(int cartId) {

        return cartDAO.removeFromCart(cartId);

    }

    public boolean clearCart(int userId) {

        return cartDAO.clearCart(userId);

    }

    public Cart getCartItem(int userId, int productId) {

        return cartDAO.getCartItem(userId, productId);

    }
    public List<CartItem> getCartItems(int userId) {

        return cartDAO.getCartItems(userId);

    }

}