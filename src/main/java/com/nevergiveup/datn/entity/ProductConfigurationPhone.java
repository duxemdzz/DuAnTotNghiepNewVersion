package com.nevergiveup.datn.entity;

import java.io.Serializable;

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
@Table(name = "Product_configuration_phone")
public class ProductConfigurationPhone implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String operatingSystem;
	String cameraBefore;
	String cameraAfter;
	String cpu;
	String ram;
	String rom;
	@JsonIgnore
	@ManyToOne @JoinColumn(name = "Product_id")
	Product product;
	@ManyToOne @JoinColumn(name = "Utilities_id")
	Utility utility;
	@ManyToOne @JoinColumn(name = "Screen_id")
	Screen screen;
	@ManyToOne @JoinColumn(name = "Generial_information_id")
	GeneralInformation generalInformation;
	@ManyToOne @JoinColumn(name = "Connect_id")
	Connect connect;
	@ManyToOne @JoinColumn(name = "Category_id")
	Category category;
	
	@ManyToOne @JoinColumn(name = "Battery_charge_id")
	BatteryCharge batteryCharge;
	
}
