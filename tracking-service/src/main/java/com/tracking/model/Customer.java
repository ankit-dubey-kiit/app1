package com.tracking.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "lu_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@Column(name="customer_name")
    private String customerName;
	
	@Column(name="customer_slug")
    private String customerSlug;
	
	@Column(name="customer_ref")
    private String customerRef;
	
	@Column(name="create_user")
    private String createUser;
	
	@Column(name="create_time")
    private Date createTime;
	
	@Column(name="update_user")
    private String updateUser;
	
	@Column(name="update_time")
    private Date updateTime;

}
