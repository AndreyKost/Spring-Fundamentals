package myexam.repository;

import myexam.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("select sum(p.price) from Product as p")
    BigDecimal findSumOfAllPrice();
}
