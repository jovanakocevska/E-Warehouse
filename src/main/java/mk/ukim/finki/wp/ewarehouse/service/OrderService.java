package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Order;
import mk.ukim.finki.wp.ewarehouse.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order placeOrder(String firstName, String lastName, String email, String address, String country,
                     String city, String zipCode, Double totalPrice, List<Long> products, String username);

    void updateOrder(Long id, String firstName, String lastName, String address, String email, String city,
                     String country, String zipcode, OrderStatus status);

    Order delete(Long id);

    Order findById(Long id);

    List<Order> listAll();

    Order cancelOrder(Long id);
}
