package com.deciphex.shopping_cart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "SKU")
	private String sku;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "SUB_TOTAL")
	private double subTotal;
}
