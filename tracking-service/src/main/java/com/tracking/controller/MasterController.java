package com.tracking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.model.Country;
import com.tracking.service.MasterService;

@RestController
@RequestMapping(value = "/master")
public class MasterController {

	private final MasterService masterService;
	
	public MasterController(MasterService masterService) {
		this.masterService = masterService;
	}
	
	@GetMapping("/country")
	public Iterable<Country> getCountry(){
		return masterService.getCountry();
	}
}
