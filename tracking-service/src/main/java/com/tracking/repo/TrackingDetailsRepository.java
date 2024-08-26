package com.tracking.repo;

import org.springframework.data.repository.CrudRepository;
import com.tracking.model.TrackingDetails;

public interface TrackingDetailsRepository extends CrudRepository<TrackingDetails,Long>{

}
