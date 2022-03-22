package mk.ukim.finki.wp.ewarehouse.service.impl;

import mk.ukim.finki.wp.ewarehouse.model.Order;
import mk.ukim.finki.wp.ewarehouse.model.OrderStatus;
import mk.ukim.finki.wp.ewarehouse.model.ProductInWarehouse;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCartProduct;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.InvalidOrderIdException;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.ewarehouse.repository.ProductRepository;
import mk.ukim.finki.wp.ewarehouse.repository.OrderRepository;
import mk.ukim.finki.wp.ewarehouse.repository.ShoppingCartProductRepository;
import mk.ukim.finki.wp.ewarehouse.repository.UserRepository;
import mk.ukim.finki.wp.ewarehouse.service.ProductInWarehouseService;
import mk.ukim.finki.wp.ewarehouse.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductInWarehouseService productInWarehouseService;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, ProductInWarehouseService productInWarehouseService, ShoppingCartProductRepository shoppingCartProductRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productInWarehouseService = productInWarehouseService;
        this.shoppingCartProductRepository = shoppingCartProductRepository;
    }

    @Override
    public Order placeOrder(String firstName, String lastName, String email,
                            String address, String country, String city,
                            String zipCode, Double totalPrice, List<Long> products, String username) {
        for (Long productId : products) {
            ShoppingCartProduct product = this.shoppingCartProductRepository.findById(productId)
                    .orElseThrow(InvalidProductIdException::new);
            List<ProductInWarehouse> list = product.getProduct().getProductsInWarehouse();
            boolean tmp = this.productInWarehouseService.lowerQuantity(list.get(list.size() - 1).
                    getId(), product.getQuantity());
            if (!tmp) {
                list.remove(list.get(list.size() - 1));
            }
        }
        Order order = new Order(this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new),
                this.shoppingCartProductRepository.findAllById(products), firstName, lastName, email,
                address, country, city, zipCode, totalPrice);
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public void updateOrder(Long id, String firstName, String lastName, String address, String email, String city,
                            String country, String zipcode, OrderStatus status) {
        Order order = this.findById(id);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setEmail(email);
        order.setCity(city);
        order.setCountry(country);
        order.setZipCode(zipcode);
        order.setStatus(status);
        this.orderRepository.save(order);
    }

    @Override
    public Order delete(Long id) {
        Order order = this.findById(id);
        this.orderRepository.delete(order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(InvalidOrderIdException::new);
    }

    @Override
    public List<Order> listAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order cancelOrder(Long id) {
        Order order = this.findById(id);
        order.setStatus(OrderStatus.CANCELED);
        return this.orderRepository.save(order);
    }
}
