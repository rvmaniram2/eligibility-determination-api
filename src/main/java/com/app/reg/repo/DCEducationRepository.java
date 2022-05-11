package com.app.reg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.AppRegEntity;
import com.app.reg.entity.DCEducation;

public interface DCEducationRepository extends JpaRepository<DCEducation, Integer> {

	DCEducation findByCaseNum(int caseNumber);

}
