package com.techstore.dao;
import com.techstore.model.CartItem;
import java.util.List;
import com.techstore.model.Cart;

public interface CartDAO {

    // Add Product to Cart
    boolean addToCart(Cart cart);

    // Get User Cart
    List<Cart> getCartByUserId(int userId);

    // Update Quantity
    boolean updateQuantity(int cartId, int quantity);

    // Remove Item
    boolean removeFromCart(int cartId);

    // Clear Cart
    boolean clearCart(int userId);

    // Check Product Already Exists
    Cart getCartItem(int userId, int productId);
 // Get Complete Cart Details
    List<CartItem> getCartItems(int userId);

}