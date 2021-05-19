package com.ninja.rmm.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.jpa.entity.Customer;

/**
 * Repository for opperations in Customer Entity
 * @author CRIS
 *
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>{

	@Query("SELECT a FROM Customer a WHERE a.customerAccount = ?1")
	List<Customer> getCustomerByAccount(String customerAccount);
	
	@Query("SELECT a FROM Customer a WHERE a.customerAccount = ?1 AND a.customerPassword = ?2 ")
	List<Customer> getCustomerByAccountAndPassword(String customerAccount, String password);
}
