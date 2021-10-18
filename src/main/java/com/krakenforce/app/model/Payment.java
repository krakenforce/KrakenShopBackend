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
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@Column(name = "cancel_url")
	private String cancelUrl;
	
	@Column(name = "success_url")
	private String successUrl;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "provider")
	private String provider;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payment" )
	private Set<Transactions> transactions;
}
