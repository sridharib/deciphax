package com.deciphex.shopping_cart.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deciphex.shopping_cart.entities.Product;
import com.deciphex.shopping_cart.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductAPI {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAll() {

		log.info("Fetching all products");
		return ResponseEntity.ok().body(productRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(long id) {

		log.info("Fetching product by id -> {}", id);
		return ResponseEntity.ok().body(productRepository.findById(id).orElse(null));
	}

	/**
	 * 
	 * @param product
	 */
	@PostMapping("/product")
	public void createProduct(@RequestBody Product product) {

		log.info("Adding product -> {}", product);
		productRepository.save(product);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void deleteById(long id) {

		log.info("Deletig product by id -> {}", id);
		productRepository.deleteById(id);
	}

}
