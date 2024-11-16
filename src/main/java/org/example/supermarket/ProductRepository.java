package org.example.supermarket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.lastModifiedDate < :thresholdDate")
    List<Product> findByLastModifiedDate(@Param("thresholdDate") LocalDateTime thresholdDate);

    @Query("SELECT p FROM Product p WHERE p.lastModifiedDate < :calculated AND p.expired = false")
    List<Product> findByLastModifiedDateeBeforeAndExpiredFalse(@Param("calculated") LocalDateTime calculated);


    @Query(value = "select  p from Product p Where p.expired = false AND p.stock > 0")
    List<Product> findAvailableProducts();

}

