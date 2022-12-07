package com.nevergiveup.datn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Float price;
	String mainImage;
	String description;
	Integer inventory;
	Boolean avaliable;
	Boolean isDisable;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_date")
	Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Update_date")
	Date updateDate;
	// mappping bảng.
	@ManyToOne
	@JoinColumn(name = "Discount_id")
	Discount discount;
	@ManyToOne
	@JoinColumn(name = "Tag_id")
	TagProduct tag;	
	@OneToMany(mappedBy = "product")
	List<Image> image;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<CartItem> cartItem;
	@ManyToOne
	@JoinColumn(name = "Category_id")
	Category category;
	@ManyToOne
	@JoinColumn(name = "Brand_id")
	Brand brand;	
	@OneToMany(mappedBy = "product")
	List<News> news;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Review> review;
	@OneToMany(mappedBy = "product")
	List<ProductConfigurationLaptop> configLaptop;
	@OneToMany(mappedBy = "product")
	List<ProductConfigurationPhone> configPhone;
	
}
