package com.nevergiveup.datn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Categories")
public class Category {
	@Id
	String id;
	String name;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> product;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Connect> connect;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Utility> utility;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Screen> screen;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<GeneralInformation> generalInformation;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<ProductConfigurationPhone> configPhone;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<BatteryCharge> batteryCharge;
}
