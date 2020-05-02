package com.deciphex.shopping_cart.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deciphex.shopping_cart.entities.Cart;
import com.deciphex.shopping_cart.entities.CartItems;
import com.deciphex.shopping_cart.repositories.CartItemsRepository;
import com.deciphex.shopping_cart.repositories.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CartAPI {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemsRepository cartItemsRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> getAll() {

		log.info("Fetching all carts");
		return ResponseEntity.ok().body(cartRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getById(long id) {

		log.info("Fetching cart by id -> {}", id);
		return ResponseEntity.ok().body(cartRepository.findById(id).orElse(null));
	}

	/**
	 * 
	 * @param cart
	 */
	@PostMapping("/cart")
	public void createCart(@RequestBody Cart cart) {

		log.info("Adding cart -> {}", cart);
		cartRepository.save(cart);
	}

	/**
	 * Method to add cart items to the cart
	 * 
	 * @param id
	 * @param sku
	 * @param quantity
	 */
	@PutMapping("/cart/{id}")
	public ResponseEntity<Object> addCartItems(@PathVariable long id, @RequestParam String sku,
			@RequestParam(required = false) Integer quantity) {

		// Get the cart
		Cart cart = cartRepository.findById(id).orElse(null);
		if (cart != null) {
			List<CartItems> cartItems = cart.getCartItems();
			CartItems item = cartItems.stream().filter(cartItem -> cartItem.getSku().equalsIgnoreCase(sku)).findFirst()
					.orElse(null);

			// If cart item found by sku
			if (item != null) {
				if (quantity != null) {
					// if quantity is not null then add it to the present quantity
					item.setQuantity(item.getQuantity() + quantity);
				} else {
					// else add 1 to the present quantity
					item.setQuantity(item.getQuantity() + 1);
				}
				cartItemsRepository.save(item);
			} else {
				// If cart item not found
				item = cartItemsRepository.findBySku(sku);
				if (item == null) {
					return ResponseEntity.badRequest().body("Invalid cart items");
				}

				if (quantity != null) {
					// if quantity is not null then add it to the present quantity
					item.setQuantity(item.getQuantity() + quantity);
				} else {
					// else add 1 to the present quantity
					item.setQuantity(item.getQuantity() + 1);
				}
				cartItemsRepository.save(item);
			}

			return ResponseEntity.ok().body(cart);
		}

		return ResponseEntity.badRequest().body("Invalid cart");
	}

	/**
	 * Method to delete cart items to the cart
	 * 
	 * @param id
	 * @param sku
	 * @param quantity
	 */
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Object> removeCartItems(@PathVariable long id, @RequestParam String sku,
			@RequestParam(required = false) Integer quantity) {

		// Get the cart
		Cart cart = cartRepository.findById(id).orElse(null);
		if (cart != null) {
			List<CartItems> cartItems = cart.getCartItems();
			CartItems item = cartItems.stream()
					.filter(cartItem -> cartItem.getSku().equalsIgnoreCase(sku) && cartItem.getQuantity() > 0)
					.findFirst().orElse(null);

			// If cart item found by sku
			if (item != null) {
				if (quantity != null) {
					// if quantity is not null then add it to the present quantity
					item.setQuantity(item.getQuantity() - quantity);
				} else {
					// else add 1 to the present quantity
					item.setQuantity(item.getQuantity() - 1);
				}
				cartItemsRepository.save(item);
			}

			return ResponseEntity.ok().body(cart);
		}

		return ResponseEntity.badRequest().body("Invalid cart");
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/cart/{id}")
	public void deleteById(long id) {

		log.info("Deletig cart by id -> {}", id);
		cartRepository.deleteById(id);
	}

}
