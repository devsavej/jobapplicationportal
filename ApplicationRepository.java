package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {

	Application findByUserIdAndJobId(int id, int jobId);

}
