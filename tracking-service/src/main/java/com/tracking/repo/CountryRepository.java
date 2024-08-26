package com.tracking.repo;

import org.springframework.data.repository.CrudRepository;

import com.tracking.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

}
