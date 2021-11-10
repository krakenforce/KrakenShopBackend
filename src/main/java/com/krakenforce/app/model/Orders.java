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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp orderDateTime;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderDetail> orderDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Timestamp getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Timestamp orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
