package com.deciphex.shopping_cart;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.deciphex.shopping_cart.entities.Product;
import com.deciphex.shopping_cart.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitData {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	private void initTestData() {

		Resource resource = resourceLoader.getResource("classpath:data/product_database.json");
		log.info("InitDemoApplication initialization logic ... Loading file -> {}", resource.getFilename());

		try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
			String jsonStr = FileCopyUtils.copyToString(reader);
			log.info("loded json data -> {}", jsonStr);

			ObjectMapper objectMapper = new ObjectMapper();
			Product[] products = objectMapper.readValue(jsonStr, Product[].class);
			
			log.info("Adding the data in DB");
			productRepository.saveAll(Arrays.asList(products));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
