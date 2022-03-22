package mk.ukim.finki.wp.ewarehouse.service.impl;

import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.ProductInWarehouse;
import mk.ukim.finki.wp.ewarehouse.model.Warehouse;
import mk.ukim.finki.wp.ewarehouse.repository.ProductInWarehouseRepository;
import mk.ukim.finki.wp.ewarehouse.service.ProductService;
import mk.ukim.finki.wp.ewarehouse.service.ProductInWarehouseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductInWarehouseServiceImpl implements ProductInWarehouseService {
    private final ProductInWarehouseRepository productInWarehouseRepository;
    private final ProductService productService;

    public ProductInWarehouseServiceImpl(ProductInWarehouseRepository productInWarehouseRepository,
                                         ProductService productService) {
        this.productInWarehouseRepository = productInWarehouseRepository;
        this.productService = productService;
    }

    public Optional<ProductInWarehouse> findById(Long id) {
        return this.productInWarehouseRepository.findById(id);
    }

    @Override
    public void addProducts(Integer quantity, Warehouse warehouse, Product product) {
        Optional<ProductInWarehouse> productInWarehouse = this.productInWarehouseRepository.
                findByProductAndWarehouse(product, warehouse);
        this.productService.setAvailability(product.getId(), true);
        if (productInWarehouse.isPresent()) {
            ProductInWarehouse piw = productInWarehouse.get();
            piw.setQuantity(piw.getQuantity() + quantity);
            this.productInWarehouseRepository.save(piw);
        } else
            this.productInWarehouseRepository.save
                    (new ProductInWarehouse(product, warehouse, quantity));
    }

    @Override
    public boolean lowerQuantity(Long id, Integer q) {
        Optional<ProductInWarehouse> productInWarehouse = this.productInWarehouseRepository.findById(id);
        if (!productInWarehouse.isPresent()) {
            return false;
        }
        ProductInWarehouse product = productInWarehouse.get();
        int quantity = product.getQuantity();
        if (quantity < q) return false;
        quantity -= q;
        if (quantity == 0) {
            this.productService.setAvailability(product.getProduct().getId(), false);
            this.productInWarehouseRepository.deleteById(product.getId());
            return false;
        } else {
            product.setQuantity(quantity);
            this.productInWarehouseRepository.save(product);
        }
        return true;
    }
}
