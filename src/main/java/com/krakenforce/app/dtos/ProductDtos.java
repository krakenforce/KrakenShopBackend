package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.CartItem;
import com.krakenforce.app.model.Category;
import com.krakenforce.app.model.ProductComment;
import com.krakenforce.app.model.ProductGameCode;
import com.krakenforce.app.model.ProductImage;
import com.krakenforce.app.model.ProductServicePack;
import com.krakenforce.app.model.Tag;

public class ProductDtos {
	private int productId;
	private String productCode;
	private String name;
	private float price;
	private float salePrice;
	private String productDetail;
	private boolean status;
	private String thumbnailImageUrl;
	private String originalProductLink;
	private ProductServicePack productServicePack;
	private Set<Category> categories;
	private Set<Tag> tags;
	private Set<ProductGameCode> productGameCodes;
	private Set<ProductImage> productImages;
	private Set<ProductComment> productComments ;
	private Set<CartItem> cartItems   ;
	
	private Set<String> tagName;

	public ProductDtos() {
		
	}

	public ProductDtos(int productId, String productCode, String name, float price, float salePrice,
			String productDetail, boolean status, String thumbnailImageUrl, String originalProductLink,
			ProductServicePack productServicePack, Set<Category> categories,
			Set<Tag> tags, Set<ProductGameCode> productGameCodes, Set<ProductImage> productImages,
			Set<ProductComment> productComments, Set<CartItem> cartItems) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.name = name;
		this.price = price;
		this.salePrice = salePrice;
		this.productDetail = productDetail;
		this.status = status;
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.originalProductLink = originalProductLink;
		this.productServicePack = productServicePack;
		this.categories = categories;
		this.tags = tags;
		this.productGameCodes = productGameCodes;
		this.productImages = productImages;
		this.productComments = productComments;
		this.cartItems = cartItems;
	}

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

	public Set<String> getTagName() {
		return tagName;
	}

	public void setTagName(Set<String> tagName) {
		this.tagName = tagName;
	}
	
	
}
