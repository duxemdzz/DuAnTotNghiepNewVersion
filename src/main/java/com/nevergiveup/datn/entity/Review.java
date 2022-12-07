package com.nevergiveup.datn.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Reviews")
public class Review implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Double rate;
	String contents;
	@Temporal(TemporalType.DATE)
	@Column(name = "Vote_date")
	Date voteDate;
	@ManyToOne @JoinColumn(name = "User_id")
	private Users users;
	@ManyToOne @JoinColumn(name = "Product_id")
	Product product;
}
