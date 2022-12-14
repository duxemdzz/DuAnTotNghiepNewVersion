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
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String fullname;
	String phone;
	String address;
	String message;
	Double total;
	Boolean isDelete;
	Integer orderStatus;
	Integer shipFee;
	Integer shipperId;
	String codePayment;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_date")
	Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Update_date")
	Date updateDate;

	@ManyToOne
	@JoinColumn(name = "User_id")
	Users users;

	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name = "Payment_id")
	Payment payment;
}
