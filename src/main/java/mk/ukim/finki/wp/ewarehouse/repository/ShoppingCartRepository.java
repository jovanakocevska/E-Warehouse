package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.ShoppingCart;
import mk.ukim.finki.wp.ewarehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser(User user);
}
