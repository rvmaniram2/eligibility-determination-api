package com.app.reg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reg.entity.AppRegEntity;

public interface AppRegEntityRepository extends JpaRepository<AppRegEntity, Integer> {

}
