package com.deciphex.shopping_cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deciphex.shopping_cart.entities.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

	public CartItems findBySku(String sku);
}
