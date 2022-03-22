package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();

    Product findbyId(Long id);

    Product create(String name, String description, String imageUrl, Boolean availability, Double price,
                   Long categoryId, Long manufacturerId);

    Product update(Long id, String name, String description, String imageUrl, Boolean availability,
                   Double price, Long categoryId, Long manufacturerId);

    Product delete(Long id);

    Product toggleAvailability(Long id);

    Product setAvailability(Long id, Boolean availability);

    List<String> getProductNames();

    List<Product> searchProductsByName(String search);

    List<Product> getProductsByCategoryManufacturerAndAvailability(Long categoryId, Long manufacturerId,
                                                                   Boolean availability);

    List<Product> findFirstThree();
}
