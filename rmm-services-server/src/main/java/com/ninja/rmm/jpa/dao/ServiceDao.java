package com.ninja.rmm.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.jpa.entity.Service;

/**
 * Repository for opperations in Service Entity
 * @author CRIS
 *
 */
public interface ServiceDao extends JpaRepository<Service, Integer>{

	@Query("SELECT a FROM Service a WHERE a.serviceName = ?1")
	List<Service> getServiceByName(String serviceName);
}
