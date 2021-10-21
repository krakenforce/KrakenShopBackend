package com.krakenforce.app.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.UniqueConstraint;

import com.krakenforce.app.enums.EGender;

@Entity
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private EGender gender;
	
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
	
	@Column(name = "reset_password_token")
	private String resetPasswordToken;
	
	@Column(name = "last_login")
	private Timestamp lastLogin;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_vip_class_id")
	private UserVipClass userVipClass;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> roleSet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserLog> userLogs;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserFeedback> userFeedbacks;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ProductComment> productComments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ProductReview> productReviews;
	
	@OneToOne(mappedBy = "user")
	private Wallet wallet;
		
	@ManyToMany
	@JoinTable(name = "user_favorite_product",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products;
	
	@OneToOne(mappedBy = "user")
	private ShoppingCart shoppingCart ;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public void setAvatarImageUrl(String avatarImageUrl) {
		this.avatarImageUrl = avatarImageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean isMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(boolean marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public Timestamp getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Timestamp registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserVipClass getUserVipClass() {
		return userVipClass;
	}

	public void setUserVipClass(UserVipClass userVipClass) {
		this.userVipClass = userVipClass;
	}

	public Set<Roles> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Roles> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<UserLog> getUserLogs() {
		return userLogs;
	}

	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

	public Set<UserFeedback> getUserFeedbacks() {
		return userFeedbacks;
	}

	public void setUserFeedbacks(Set<UserFeedback> userFeedbacks) {
		this.userFeedbacks = userFeedbacks;
	}


	public Set<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(Set<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public Set<ProductReview> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(Set<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	
	
	
}
