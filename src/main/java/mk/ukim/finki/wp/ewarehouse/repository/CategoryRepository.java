package mk.ukim.finki.wp.ewarehouse.repository;

import mk.ukim.finki.wp.ewarehouse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
