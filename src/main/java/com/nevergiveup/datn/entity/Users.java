package com.nevergiveup.datn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Users implements Serializable{
	@Id	
	String username;
	String password;
	String firstName;
	String lastName;
	Integer gender;
	@Column(name="`Email`")
	String email;
	String phone;
	String photo;
	Boolean isActive;
	Boolean isDisable;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_date")
	Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Update_date")
	Date updateDate;
	@JsonIgnore
	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	List<Authority> authorities;
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<Cart> cart;
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<Order> orders;
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<News> news;
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<Review> review;
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<UserAddress> address;
}
