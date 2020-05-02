package com.deciphex.shopping_cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deciphex.shopping_cart.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
