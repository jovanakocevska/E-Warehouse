package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.ProductInWarehouse;
import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInWarehouseRepository extends JpaRepository<ProductInWarehouse, Long> {
    Optional<ProductInWarehouse> findByProductAndWarehouse(Product product, Warehouse warehouse);
}
