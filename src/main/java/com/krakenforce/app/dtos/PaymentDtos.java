package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.Set;

import com.krakenforce.app.model.Transactions;
import com.krakenforce.app.model.Wallet;

public class PaymentDtos {
	private int id;
	private Wallet wallet;
	private int walletId;
	private int userId;
	
	private float totalPrice;
	private String cancelUrl;
	private String successUrl;
	private Timestamp createdAt;
	private String provider;
	private Set<Transactions> transactions;

	public PaymentDtos() {
		
	}

	public PaymentDtos(int id, Wallet wallet, float totalPrice, String cancelUrl, String successUrl,
			Timestamp createdAt, String provider, Set<Transactions> transactions) {
		super();
		this.id = id;
		this.wallet = wallet;
		this.totalPrice = totalPrice;
		this.cancelUrl = cancelUrl;
		this.successUrl = successUrl;
		this.createdAt = createdAt;
		this.provider = provider;
		this.transactions = transactions;
	}

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

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
