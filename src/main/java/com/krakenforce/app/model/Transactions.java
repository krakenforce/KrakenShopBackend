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
@Table(name = "transactions")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "confirms")
	private boolean comfirms;
	
	@Column(name = "provider_fee")
	private float provider_fee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "status")
	private boolean status;
}
