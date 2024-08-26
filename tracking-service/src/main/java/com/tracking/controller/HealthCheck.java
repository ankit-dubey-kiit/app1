package com.tracking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HealthCheck {

	@GetMapping("/health")
	public String getHealth() {
		return "{STATUS:UP AND RUNNING}";
	}
}
