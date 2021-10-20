package com.krakenforce.app.model;

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
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "sale_price")
	private float salePrice;
	
	@Column(name = "product_detail")
	private String productDetail;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "thumbnail_image_url")
	private String thumbnailImageUrl;
	
	@Column(name = "original_product_link")
	private String originalProductLink;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_pack_id")
	private ProductServicePack productServicePack;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inventory_id")
	private ProductInventory productInventory;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "category_product",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tag_product",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductGameCode> productGameCodes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductImage> productImages;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductDetail> productDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductWarranty> productWarranties;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductComment> productComments ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<CartItem> cartItems   ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductReview> productReviews;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public String getOriginalProductLink() {
		return originalProductLink;
	}

	public void setOriginalProductLink(String originalProductLink) {
		this.originalProductLink = originalProductLink;
	}

	public ProductServicePack getProductServicePack() {
		return productServicePack;
	}

	public void setProductServicePack(ProductServicePack productServicePack) {
		this.productServicePack = productServicePack;
	}

	public ProductInventory getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(ProductInventory productInventory) {
		this.productInventory = productInventory;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<ProductGameCode> getProductGameCodes() {
		return productGameCodes;
	}

	public void setProductGameCodes(Set<ProductGameCode> productGameCodes) {
		this.productGameCodes = productGameCodes;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

	public Set<ProductWarranty> getProductWarranties() {
		return productWarranties;
	}

	public void setProductWarranties(Set<ProductWarranty> productWarranties) {
		this.productWarranties = productWarranties;
	}

	public Set<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(Set<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Set<ProductReview> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(Set<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}
	
	

}