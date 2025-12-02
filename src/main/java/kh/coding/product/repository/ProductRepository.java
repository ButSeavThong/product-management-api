package kh.coding.product.repository;
import kh.coding.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByUuidAndDeletedFalse(String uuid);
    Page<Product> findAllByDeletedFalse(Pageable pageable);
    boolean existsByUuidAndDeletedFalse(String uuid);
}