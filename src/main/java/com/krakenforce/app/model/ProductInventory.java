package com.krakenforce.app.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_inventory")
public class ProductInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "import_price")
	private float importPrice;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "modified_at")
	private Timestamp modifiedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productInventory")
	private Set<Product> products;
}
