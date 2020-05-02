package com.deciphex.shopping_cart.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deciphex.shopping_cart.entities.CartItems;
import com.deciphex.shopping_cart.repositories.CartItemsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CartItemsAPI {

	@Autowired
	private CartItemsRepository cartItemsRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/cartitems")
	public ResponseEntity<List<CartItems>> getAll() {

		log.info("Fetching all cart items");
		return ResponseEntity.ok().body(cartItemsRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/cartitems/{id}")
	public ResponseEntity<CartItems> getById(long id) {

		log.info("Fetching cart by id -> {}", id);
		return ResponseEntity.ok().body(cartItemsRepository.findById(id).orElse(null));
	}

	/**
	 * 
	 * @param cartItems
	 */
	@PostMapping("/cartitems")
	public void createCartItems(@RequestBody CartItems cartItems) {

		log.info("Adding cart -> {}", cartItems);
		cartItemsRepository.save(cartItems);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/cartitems/{id}")
	public void deleteById(long id) {

		log.info("Deletig cart by id -> {}", id);
		cartItemsRepository.deleteById(id);
	}

}
