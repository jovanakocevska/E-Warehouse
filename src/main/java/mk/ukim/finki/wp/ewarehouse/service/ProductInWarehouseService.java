package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ProductInWarehouse;
import mk.ukim.finki.wp.ewarehouse.model.Warehouse;

import java.util.Optional;

public interface ProductInWarehouseService {
    Optional<ProductInWarehouse> findById(Long id);

    boolean lowerQuantity(Long id, Integer q);

    void addProducts(Integer quantity, Warehouse warehouse, Product product);
}
