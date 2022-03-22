package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.Category;
import mk.ukim.finki.wp.ewarehouse.model.Product;
import mk.ukim.finki.wp.ewarehouse.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select distinct name from Product ")
    List<String> getProductNames();

    List<Product> findAllByNameContains(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);

    List<Product> findAllByCategoryAndManufacturer(Category category, Manufacturer manufacturer);

    List<Product> findAllByCategoryAndManufacturerAndAvailability(Category category, Manufacturer manufacturer, Boolean availability);

    List<Product> findAllByAvailability(Boolean availability);

    List<Product> findAllByCategoryAndAvailability(Category category, Boolean availability);

    List<Product> findAllByManufacturerAndAvailability(Manufacturer manufacturer, Boolean availability);

    List<Product> findFirst3ByOrderById();
}
