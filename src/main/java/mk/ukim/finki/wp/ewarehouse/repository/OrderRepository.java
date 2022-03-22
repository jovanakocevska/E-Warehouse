package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
