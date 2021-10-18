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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.krakenforce.app.enums.GenderEnum;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "hash_password")
	private String hashPassword;
	
	@Column(name = "password_salt")
	private String passwordSalt;
	
	@Column(name = "fist_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "identity_number")
	private String identityNumber;
	
	@Column(name = "gender")
	private GenderEnum gender;
	
	@Column(name = "avatar_image_url")
	private String avatarImageUrl;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "job")
	private String job;
	
	@Column(name = "marriage_status")
	private boolean marriageStatus;
	
	@Column(name = "registered_at")
	private Timestamp registeredAt;
	
	@Column(name = "last_login")
	private Timestamp lastLogin;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_vip_class_id")
	private UserVipClass userVipClass;
	
	@ManyToMany
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> roleSet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserLog> userLogs;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserFeedback> userFeedbacks;
	
	@ManyToMany
	@JoinTable(name = "department_employee",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id"))
	private Set<Department> departments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ProductComment> productComments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<CommentReply> commentReplies ;
	
	@OneToOne(mappedBy = "user")
	private Wallet wallet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Payment> payments  ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Orders> orders;
	
	@ManyToMany
	@JoinTable(name = "user_favorite_product",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ShoppingCart> shoppingCarts ;
}
