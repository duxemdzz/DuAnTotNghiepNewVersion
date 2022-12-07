package com.nevergiveup.datn.entity;

import java.io.Serializable;
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
@Table(name = "Battery_charge")
public class BatteryCharge implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String batteryType;
	String batteryCapacity;
	String chargeSupport;
	@JsonIgnore
	@OneToMany(mappedBy = "batteryCharge")
	List<ProductConfigurationPhone> configPhone;
	@ManyToOne @JoinColumn(name = "Category_id")
	Category category;
	
}
 