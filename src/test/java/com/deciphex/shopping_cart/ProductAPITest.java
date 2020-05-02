package com.deciphex.shopping_cart;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.deciphex.shopping_cart.entities.Product;
import com.deciphex.shopping_cart.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductAPITest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository productRepository;

	@Test
	public void findAll() throws Exception {
		// given
		Product product = new Product();
		product.setId(1);
		product.setInStock(false);
		product.setCurrency("GBP");
		product.setDescription("test");
		product.setImageUrl("imgUrl");

		List<Product> products = Arrays.asList(product);
		given(productRepository.findAll()).willReturn(products);

		// when + then
		this.mockMvc.perform(get("/products")).andExpect(status().isOk())
				.andExpect(content().json("[{'id': 1,'currency': 'GBP';'description': 'test'}]"));
	}

}
