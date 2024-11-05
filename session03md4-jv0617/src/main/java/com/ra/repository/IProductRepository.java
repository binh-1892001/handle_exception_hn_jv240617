package com.ra.repository;

import com.ra.model.entity.customer.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long>
{
    Page<Product> findAllByCategoryNameContains(String name, Pageable pageable);
}
