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
@Table(name = "Connect")
public class Connect implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Boolean support;
	String sim;
	String  chargePort;
	String headphoneJack;
	@ManyToOne @JoinColumn(name = "Category_id")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "connect")
	List<ProductConfigurationPhone> configPhone;
	
	
	
}
