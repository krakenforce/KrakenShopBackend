package com.krakenforce.app.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.krakenforce.app.model.Users;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String email;
	private int walletId;
	private int cartId;
	private float walletBalance;
	private String avatarImageUrl;
	private String vipClassName;
	private int discountPercentage;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int id, String username, String email, int cartId, int walletId, float walletBalance,
			String avatarImageUrl, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.cartId = cartId;
		this.walletId = walletId;
		this.walletBalance = walletBalance;
		this.avatarImageUrl = avatarImageUrl;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Users user) {
		List<GrantedAuthority> authorities = user.getRoleSet().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getEmail(),
				user.getShoppingCart().getId(), user.getWallet().getId(), user.getWallet().getBalance(),
				user.getAvatarImageUrl(), user.getHashPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public float getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(float walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public void setAvatarImageUrl(String avatarImageUrl) {
		this.avatarImageUrl = avatarImageUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) obj;
		return Objects.equals(id, user.id);
	}

	public String getVipClassName() {
		return vipClassName;
	}

	public void setVipClassName(String vipClassName) {
		this.vipClassName = vipClassName;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
