package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Order;
import mk.ukim.finki.wp.ewarehouse.model.User;

import java.util.List;

public interface UserService {
    User register(String username, String password, String repeatPassword, String role);

    User login(String username, String password);

    User updateUser(String username, String password, String firstName,
                    String lastName,
                    String address,
                    String email);

    User adminUserUpdate(String username, String firstName,
                         String lastName,
                         String address,
                         String email);

    User getUser(String username);

    List<User> getAllUsers();

    User findUserById(String username);

    void deleteUser(String username);

    List<Order> listAllUserOrders(String username);
}
