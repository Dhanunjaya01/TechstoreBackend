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

        try {

            Address address = request.getAddress();

            address.setUserId(request.getUserId());

            int addressId = addressService.saveAddress(address);

            if (addressId == 0) {

                return false;

            }

            Order order = new Order();

            order.setUserId(request.getUserId());

            order.setAddressId(addressId);

            order.setOrderNumber(
                    "ORD-" + UUID.randomUUID().toString().substring(0, 8));

            order.setTotalAmount(
                    BigDecimal.valueOf(request.getTotalAmount()));

            order.setOrderStatus("Order Placed");

            order.setPaymentStatus("Pending");

            int orderId = orderService.placeOrder(order);

            if (orderId == 0) {

                return false;

            }

            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem cartItem : request.getCartItems()) {

                OrderItem item = new OrderItem();

                item.setOrderId(orderId);

                item.setProductId(cartItem.getProductId());

                item.setQuantity(cartItem.getQuantity());

                item.setPrice(
                        BigDecimal.valueOf(cartItem.getPrice()));

                item.setSubtotal(
                        BigDecimal.valueOf(
                                cartItem.getPrice() * cartItem.getQuantity()));

                orderItems.add(item);

            }

            if (!orderItemService.addOrderItems(orderItems)) {

                return false;

            }

            Payment payment = request.getPayment();

            payment.setOrderId(orderId);

            payment.setTransactionId(
                    "TXN-" + UUID.randomUUID().toString().substring(0, 8));

            if (!paymentService.savePayment(payment)) {

                return false;

            }

            cartService.clearCart(request.getUserId());

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}