package com.deciphex.shopping_cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deciphex.shopping_cart.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
