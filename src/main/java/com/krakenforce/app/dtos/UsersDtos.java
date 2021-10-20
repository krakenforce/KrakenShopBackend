package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.krakenforce.app.enums.EGender;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ProductComment;
import com.krakenforce.app.model.Roles;
import com.krakenforce.app.model.ShoppingCart;
import com.krakenforce.app.model.UserFeedback;
import com.krakenforce.app.model.UserLog;
import com.krakenforce.app.model.UserVipClass;
import com.krakenforce.app.model.Wallet;

public class UsersDtos {
	private int userId;
	private String username;
	private String email;
	private String hashPassword;
	private String passwordSalt;
	private String firstName;
	private String lastName;
	private String phone;
	private String identityNumber;
	private EGender gender;
	private String avatarImageUrl;
	private String address;
	private String job;
	private boolean marriageStatus;
	private Timestamp registeredAt;
	private Timestamp lastLogin;
	private boolean status;
	private UserVipClass userVipClass;
	private Set<Roles> roleSet = new HashSet<Roles>();
	private Set<UserLog> userLogs;
	private Set<UserFeedback> userFeedbacks;
	private Set<ProductComment> productComments;
	private Wallet wallet;
	private Set<Product> products;
	private Set<ShoppingCart> shoppingCarts ;

	public UsersDtos() {
		
	}

	public UsersDtos(int userId, String username, String email, String hashPassword, String passwordSalt,
			String firstName, String lastName, String phone, String identityNumber, EGender gender,
			String avatarImageUrl, String address, String job, boolean marriageStatus, Timestamp registeredAt,
			Timestamp lastLogin, boolean status, UserVipClass userVipClass, Set<Roles> roleSet, Set<UserLog> userLogs,
			Set<UserFeedback> userFeedbacks, Set<ProductComment> productComments, Wallet wallet, Set<Product> products,
			Set<ShoppingCart> shoppingCarts) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.hashPassword = hashPassword;
		this.passwordSalt = passwordSalt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.identityNumber = identityNumber;
		this.gender = gender;
		this.avatarImageUrl = avatarImageUrl;
		this.address = address;
		this.job = job;
		this.marriageStatus = marriageStatus;
		this.registeredAt = registeredAt;
		this.lastLogin = lastLogin;
		this.status = status;
		this.userVipClass = userVipClass;
		this.roleSet = roleSet;
		this.userLogs = userLogs;
		this.userFeedbacks = userFeedbacks;
		this.productComments = productComments;
		this.wallet = wallet;
		this.products = products;
		this.shoppingCarts = shoppingCarts;
	}

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

	public Set<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
	
	
	
}
