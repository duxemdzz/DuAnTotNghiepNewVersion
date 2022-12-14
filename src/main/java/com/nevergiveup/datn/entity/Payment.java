package com.nevergiveup.datn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Payment")
public class Payment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String detail;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_date")
	Date createDate;
	String adminCreate;
	Boolean statusUsed;
	@JsonIgnore
	@OneToMany(mappedBy = "payment")
	List<Order> oder;
}
