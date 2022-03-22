package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCart;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCartProduct;
import mk.ukim.finki.wp.ewarehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long> {
    Optional<ShoppingCartProduct> findByProductAndShoppingCartAndWarehouse(Product product,
                                                                           ShoppingCart shoppingCart,
                                                                           Warehouse warehouse);
}
