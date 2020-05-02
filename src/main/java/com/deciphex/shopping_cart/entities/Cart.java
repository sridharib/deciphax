package com.deciphex.shopping_cart.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "SUB_TOTAL")
	private double subTotal;

	@Column(name = "POST_AND_TAX")
	private double postAndTax;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "TOTAL")
	private double total;

	@OneToMany
	private List<CartItems> cartItems;
}
