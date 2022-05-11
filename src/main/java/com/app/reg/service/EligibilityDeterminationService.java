package com.app.reg.service;

import com.app.reg.dto.PlanInfoDto;

public interface EligibilityDeterminationService {

	PlanInfoDto getEligibilityDetermination(int caseNumber);

}
