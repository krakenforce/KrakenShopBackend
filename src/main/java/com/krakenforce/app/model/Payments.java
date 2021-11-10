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
@Table(name = "payment")
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@Column(name = "cancel_url")
	private String cancelUrl;
	
	@Column(name = "success_url")
	private String successUrl;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdAt;
	
	@Column(name = "provider")
	private String provider;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payment" )
	private Set<Transactions> transactions;

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

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Set<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transactions> transactions) {
		this.transactions = transactions;
	}
	
	
}
