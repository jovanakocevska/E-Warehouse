package mk.ukim.finki.wp.ewarehouse.service.impl;

import mk.ukim.finki.wp.ewarehouse.model.ProductInWarehouse;
import mk.ukim.finki.wp.ewarehouse.model.Warehouse;
import mk.ukim.finki.wp.ewarehouse.repository.ProductInWarehouseRepository;
import mk.ukim.finki.wp.ewarehouse.repository.WarehouseRepository;
import mk.ukim.finki.wp.ewarehouse.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ProductInWarehouseRepository productInWarehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                ProductInWarehouseRepository productInWarehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productInWarehouseRepository = productInWarehouseRepository;
    }

    @Override
    public List<Warehouse> listAll() {
        return this.warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long id) {
        return this.warehouseRepository.findById(id).get();
    }

    @Override
    public Warehouse create(String name) {
        return this.warehouseRepository.save(new Warehouse(name));
    }

    @Override
    public Warehouse update(Long id, String name) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        warehouse.setName(name);
        return this.warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse delete(Long id) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        for (ProductInWarehouse product : warehouse.getProductInWarehouses()) {
            this.productInWarehouseRepository.delete(product);
        }
        this.warehouseRepository.deleteById(id);
        return warehouse;
    }
}
