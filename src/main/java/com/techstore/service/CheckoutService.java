package com.techstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.techstore.model.Address;
import com.techstore.model.CartItem;
import com.techstore.model.CheckoutRequest;
import com.techstore.model.Order;
import com.techstore.model.OrderItem;
import com.techstore.model.Payment;

public class CheckoutService {

    private AddressService addressService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    private PaymentService paymentService;
    private CartService cartService;

    public CheckoutService() {

        addressService = new AddressService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
        paymentService = new PaymentService();
        cartService = new CartService();

    }

    public boolean checkout(CheckoutRequest request) {
        
    	public boolean checkout(CheckoutRequest request) {

    	    try {

    	        System.out.println("========== CHECKOUT START ==========");
    	        System.out.println("User ID: " + request.getUserId());

    	        Address address = request.getAddress();

    	        System.out.println("Saving Address...");

    	        address.setUserId(request.getUserId());

    	        int addressId = addressService.saveAddress(address);

    	        System.out.println("Address ID = " + addressId);

    	        if (addressId == 0) {
    	            System.out.println("ADDRESS FAILED");
    	            return false;
    	        }

    	        Order order = new Order();

    	        order.setUserId(request.getUserId());
    	        order.setAddressId(addressId);
    	        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8));
    	        order.setTotalAmount(BigDecimal.valueOf(request.getTotalAmount()));
    	        order.setOrderStatus("Order Placed");
    	        order.setPaymentStatus("Pending");

    	        System.out.println("Placing Order...");

    	        int orderId = orderService.placeOrder(order);

    	        System.out.println("Order ID = " + orderId);

    	        if (orderId == 0) {
    	            System.out.println("ORDER FAILED");
    	            return false;
    	        }

    	        // Your existing orderItems code...

    	        System.out.println("Saving Order Items...");

    	        if (!orderItemService.addOrderItems(orderItems)) {
    	            System.out.println("ORDER ITEMS FAILED");
    	            return false;
    	        }

    	        Payment payment = request.getPayment();

    	        payment.setOrderId(orderId);
    	        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8));

    	        System.out.println("Saving Payment...");

    	        if (!paymentService.savePayment(payment)) {
    	            System.out.println("PAYMENT FAILED");
    	            return false;
    	        }

    	        System.out.println("Clearing Cart...");

    	        cartService.clearCart(request.getUserId());

    	        System.out.println("CHECKOUT SUCCESS");

    	        return true;

    	    } catch (Exception e) {

    	        e.printStackTrace();

    	    }

    	    return false;
    	}

}