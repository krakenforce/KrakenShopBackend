package com.krakenforce.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_game_code_id")
	private ProductGameCode productGameCode;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "status")
	private boolean status;
}
