package com.app.reg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.DCChilds;

public interface DCChildsRepository extends JpaRepository<DCChilds, Integer> {

	List<DCChilds> findByCaseNum(int caseNumber);

}
