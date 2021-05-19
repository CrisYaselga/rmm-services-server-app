package com.ninja.rmm.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.jpa.entity.CustomerService;

/**
 * Repository for opperations in CustomerService Entity
 * @author CRIS
 *
 */
public interface CustomerServiceDao extends JpaRepository<CustomerService, Integer>{

	@Query("SELECT a FROM CustomerService a WHERE a.customerId.customerAccount = ?1")
	List<CustomerService> getServicesByCustomer(String customerAccount);
	
	@Query("SELECT a FROM CustomerService a WHERE a.customerId.customerAccount = ?1 AND a.serviceId.serviceName = ?2")
	List<CustomerService> getServicesByCustomerAndService(String customerAccount, String nameService);
}
