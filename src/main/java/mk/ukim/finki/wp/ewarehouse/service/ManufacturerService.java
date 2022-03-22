package mk.ukim.finki.wp.ewarehouse.service;

import mk.ukim.finki.wp.ewarehouse.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> listAll();

    Manufacturer findbyId(Long id);

    Manufacturer create(String name);

    Manufacturer update(Long id, String name);

    Manufacturer delete(Long id);
}
