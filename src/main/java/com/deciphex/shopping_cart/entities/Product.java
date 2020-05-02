package com.deciphex.shopping_cart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "SKU")
	private String sku;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IMAGE_URL")
	private String imageUrl;

	@Column(name = "IN_STOCK")
	private boolean inStock;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "PRICE")
	private double price;
}
