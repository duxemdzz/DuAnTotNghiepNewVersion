package com.nevergiveup.datn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Float price;
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "Product_id")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Order_id")
	Order order;
}
