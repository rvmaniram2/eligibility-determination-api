package com.app.reg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.reg.dto.PlanInfoDto;
import com.app.reg.service.EligibilityDeterminationService;

@RestController
public class EligibilityDeterminationController {

	@Autowired
	private EligibilityDeterminationService service;

	@GetMapping("/eligibility/determination/{caseNumber}")
	public PlanInfoDto getEligibilityDetermination(@PathVariable int caseNumber) {
		return service.getEligibilityDetermination(caseNumber);
	}
}
