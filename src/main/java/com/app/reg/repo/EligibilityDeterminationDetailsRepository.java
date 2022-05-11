package com.app.reg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.EligibilityDeterminationDetails;

public interface EligibilityDeterminationDetailsRepository
		extends JpaRepository<EligibilityDeterminationDetails, Integer> {

}
