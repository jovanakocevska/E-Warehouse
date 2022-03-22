package mk.ukim.finki.wp.ewarehouse.service.impl;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCart;
import mk.ukim.finki.wp.ewarehouse.model.ShoppingCartProduct;
import mk.ukim.finki.wp.ewarehouse.repository.ShoppingCartProductRepository;
import mk.ukim.finki.wp.ewarehouse.repository.WarehouseRepository;
import mk.ukim.finki.wp.ewarehouse.service.ShoppingCartProductService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartProductServiceImpl implements ShoppingCartProductService {
    private final ShoppingCartProductRepository shoppingCartProductRepository;
    private final WarehouseRepository warehouseRepository;

    public ShoppingCartProductServiceImpl(ShoppingCartProductRepository shoppingCartProductRepository,
                                          WarehouseRepository warehouseRepository) {
        this.shoppingCartProductRepository = shoppingCartProductRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public ShoppingCartProduct findById(Long id) {
        return this.shoppingCartProductRepository.findById(id).get();
    }

    @Override
    public ShoppingCartProduct create(Integer quantity, Product product, ShoppingCart shoppingCart, Long warehouse) {
        return this.shoppingCartProductRepository.save(new ShoppingCartProduct(quantity, product, shoppingCart,
                this.warehouseRepository.findById(warehouse).get()));
    }

    @Override
    public ShoppingCartProduct updateQuantityOfProduct(Long id, Integer quantity) {
        ShoppingCartProduct product = this.shoppingCartProductRepository.findById(id).get();
        product.setQuantity(quantity);
        return this.shoppingCartProductRepository.save(product);
    }
}
