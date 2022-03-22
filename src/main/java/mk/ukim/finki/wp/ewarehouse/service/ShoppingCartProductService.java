package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCart;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCartProduct;

public interface ShoppingCartProductService {
    ShoppingCartProduct findById(Long id);

    ShoppingCartProduct create(Integer quantity, Product product, ShoppingCart shoppingCart, Long warehouse);

    ShoppingCartProduct updateQuantityOfProduct(Long id, Integer quantity);
}
