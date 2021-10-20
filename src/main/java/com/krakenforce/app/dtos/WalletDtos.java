package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.Orders;
import com.krakenforce.app.model.Payment;
import com.krakenforce.app.model.Users;

public class WalletDtos {
	private int id;
	private Users user;
	private float balance;
	private boolean status;
	private Set<Payment> payments;
	private Set<Orders> orders;

	public WalletDtos() {
		
	}

	public WalletDtos(int id, Users user, float balance, boolean status, Set<Payment> payments, Set<Orders> orders) {
		super();
		this.id = id;
		this.user = user;
		this.balance = balance;
		this.status = status;
		this.payments = payments;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
	
	
}
