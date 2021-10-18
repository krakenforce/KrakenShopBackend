package com.krakenforce.app.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@Column(name = "order_datetime")
	private Timestamp orderDateTime;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderDetail> orderDetails;
}
