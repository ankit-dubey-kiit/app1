package com.tracking.service.impl;

import org.springframework.stereotype.Service;

import com.tracking.model.Country;
import com.tracking.repo.CountryRepository;
import com.tracking.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService{

private final CountryRepository countryRepo;
	
	public MasterServiceImpl(CountryRepository countryRepo) {
		this.countryRepo = countryRepo;
	}
	
	@Override
	public Iterable<Country> getCountry() {
		return countryRepo.findAll();
	}

	
}
