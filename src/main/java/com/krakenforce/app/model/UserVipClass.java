package com.krakenforce.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_vip_class")
public class UserVipClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "discount_percentage")
	private int discountPercentage;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(mappedBy = "userVipClass", fetch = FetchType.LAZY)
	private Set<Users> usersSet;
}
