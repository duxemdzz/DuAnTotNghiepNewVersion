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

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "General_information")
public class GeneralInformation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String design;
	String material;
	Float width;
	Float height;
	Float thick;
	Float weight;
	@Temporal(TemporalType.DATE)
	@Column(name = "Launch_date")
	Date launchDate;
	
	@ManyToOne @JoinColumn(name = "Category_id")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "generalInformation")
	List<ProductConfigurationLaptop> configLaptop;
	@JsonIgnore
	@OneToMany(mappedBy = "generalInformation")
	List<ProductConfigurationPhone> configPhone;
}
