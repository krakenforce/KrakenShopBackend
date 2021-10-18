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
	@JoinColumn(name = "category_id")
	private MainCategory category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_pack_id")
	private ProductServicePack productServicePack;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inventory_id")
	private ProductInventory productInventory;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sub_category_product",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "sub_category_id"))
	private Set<SubCategory> subCategories;
	
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
	private Set<OrderDetail> orderDetails  ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<CartItem> cartItems   ;

}