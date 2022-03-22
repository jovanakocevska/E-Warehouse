package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCart;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCartProduct;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart findById(Long id);

    ShoppingCart deleteShoppingCart(Long id);

    ShoppingCart deleteProduct(ShoppingCartProduct product, Long id);

    ShoppingCart deleteAllProducts(Long id);

    ShoppingCart getShoppingCart(String username);

    List<Product> listAllProductsInShoppingCart(Long id);

    List<ShoppingCartProduct> listAllProducts(Long id);

    void addProductToShoppingCart(String username, Long id, Long fromWarehouse);
}
