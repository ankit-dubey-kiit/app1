package com.tracking.repo;

import org.springframework.data.repository.CrudRepository;

import com.tracking.model.TrackingHistory;

public interface TrackingHistoryRepository extends CrudRepository<TrackingHistory, Long>{

}
