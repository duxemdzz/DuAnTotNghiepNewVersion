package com.nevergiveup.datn.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "Product_configuration_laptop")
public class ProductConfigurationLaptop implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String operatingSystem;
	String ram;
	String cpu;
	String hardDriver;
	String maxHardDriver;
	String pin;
	@JsonIgnore
	@ManyToOne @JoinColumn(name = "Product_id")
	Product product;
	@ManyToOne @JoinColumn(name = "Connect_id")
	Connect connect;
	@ManyToOne @JoinColumn(name = "Utilities_id")
	Utility utility;
	@ManyToOne @JoinColumn(name = "Screen_id")
	Screen screen;
	@ManyToOne @JoinColumn(name = "Generial_information_id")
	GeneralInformation generalInformation;
	@ManyToOne @JoinColumn(name = "Graphics_sound_id")
	GraphicsSound graphicsSound;
}
