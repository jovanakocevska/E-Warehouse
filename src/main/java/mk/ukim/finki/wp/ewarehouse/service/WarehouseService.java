package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> listAll();

    Warehouse findById(Long id);

    Warehouse create(String name);

    Warehouse update(Long id, String name);

    Warehouse delete(Long id);
}
