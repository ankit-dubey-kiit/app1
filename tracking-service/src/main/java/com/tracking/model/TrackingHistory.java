package com.tracking.model;

import java.sql.Date;

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
@Table(name = "tracking_history")
public class TrackingHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tracking_id", nullable = false)
    private TrackingDetails trackingDetails;

    
    @Column(name = "tracking_status", nullable = false)
    private String trackingStatus;
    
    @Column(name = "comments", nullable = true)
    private String comments;

    @Column(name = "create_user", length = 255)
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

}
