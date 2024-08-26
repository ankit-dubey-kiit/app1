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
@Table(name = "lu_country")
public class Country{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long countryId;
	
	@Column(name="country_code", length=2, nullable=false, unique=true)
    private String countryCode;
	
	@Column(name="display_name", length=255, nullable=true, unique=false)
    private String displayName;
	
	@Column(name="create_user")
    private String createUser;
	
	@Column(name="create_time")
    private Date createTime;
	
	@Column(name="update_user")
    private String updateUser;
	
	@Column(name="update_time")
    private Date updateTime;
	
}
