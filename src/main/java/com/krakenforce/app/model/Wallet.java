package com.krakenforce.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Users user;
	
	@Column(name = "balance")
	private float balance;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
	private Set<Payment> payments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
	private Set<Orders> orders;
}
