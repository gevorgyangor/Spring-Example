package com.example.eshop.repository;

import com.example.eshop.model.Brand;
import com.example.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
