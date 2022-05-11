package com.app.reg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.DCPlan;

public interface DCPlanRepository extends JpaRepository<DCPlan, Integer> {

	DCPlan findByCaseNum(int caseNumber);

}
