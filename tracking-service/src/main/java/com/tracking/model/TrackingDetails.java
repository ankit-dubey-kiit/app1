package com.tracking.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tracking_details")
public class TrackingDetails {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "tracking_id")
	    private Long id;

	    @Column(name = "origin_country_code", nullable = false, length = 2, unique = true)
	    private String originCountryId;

	    @Column(name = "destination_country_code", nullable = false, length = 2, unique = true)
	    private String destinationCountryId;

	    @Column(name = "weight")
	    private Double weight;

	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    private Customer customer;

	    @Column(name = "tracking_number", nullable = false, length = 16, unique = true)
	    private String trackingNumber;
	    
	    @Column(name = "create_user", length = 255)
	    private String createUser;

	    @Column(name = "create_time")
	    private Date createTime;

	    @Column(name = "update_user", length = 255)
	    private String updateUser;

	    @Column(name = "update_time")
	    private Date updateTime;
}
