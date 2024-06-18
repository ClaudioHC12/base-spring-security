package com.curso.base_spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.base_spring_security.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
