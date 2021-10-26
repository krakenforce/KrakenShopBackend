package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Payments;

public class TransactionsDtos {
	private int id;
	private Payments payment;
	private String description;
	private float total;
	private boolean comfirms;
	private float provider_fee;
	private Timestamp createdAt;
	private boolean status;

	public TransactionsDtos() {
		
	}

	public TransactionsDtos(int id, Payments payment, String description, float total, boolean comfirms,
			float provider_fee, Timestamp createdAt, boolean status) {
		super();
		this.id = id;
		this.payment = payment;
		this.description = description;
		this.total = total;
		this.comfirms = comfirms;
		this.provider_fee = provider_fee;
		this.createdAt = createdAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isComfirms() {
		return comfirms;
	}

	public void setComfirms(boolean comfirms) {
		this.comfirms = comfirms;
	}

	public float getProvider_fee() {
		return provider_fee;
	}

	public void setProvider_fee(float provider_fee) {
		this.provider_fee = provider_fee;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
