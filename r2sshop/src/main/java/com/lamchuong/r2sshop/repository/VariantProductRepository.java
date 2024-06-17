package com.lamchuong.r2sshop.repository;

import com.lamchuong.r2sshop.model.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct, Long> {
    List<VariantProduct> findByProductId(Long productId);
}
