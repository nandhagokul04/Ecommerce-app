package org.jsp.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String catagory;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	private String image_url;
	@ManyToOne
	@JoinColumn(name="merchant_id")
	@JsonIgnore
	private Merchant merchant;
	@ManyToOne
	@JoinColumn(name="product_id")
	@JsonIgnore
	private ProductCart cart;
	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Orders order;
	
}
