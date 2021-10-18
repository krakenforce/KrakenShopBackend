package com.krakenforce.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "support")
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "content_link")
	private String contentLink;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "support_category_id")
	private SupportCategory supportCategory;
	
	@Column(name = "status")
	private boolean status;
}
