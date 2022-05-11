package com.app.reg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.DCIncome;

public interface DCIncomeRepository extends JpaRepository<DCIncome, Integer> {

	DCIncome findByCaseNum(int caseNumber);

}
