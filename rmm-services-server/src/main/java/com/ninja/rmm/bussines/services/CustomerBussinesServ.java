package com.ninja.rmm.bussines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninja.rmm.jpa.dao.CustomerDao;
import com.ninja.rmm.jpa.entity.Customer;

/**
 * Transactional Service to access the to transacctions of CustomerDao
 * @author CRIS
 *
 */
@Service
@Transactional
public class CustomerBussinesServ {
	
	@Autowired
	CustomerDao customerDao;

	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
	/**
	 * Get a Customer by Account if dont exists return null
	 * @param customerAccount
	 * @return
	 */
	@Transactional(readOnly = true)
	public Customer getCustomerByAccount(String customerAccount) {
		if(customerAccount == null || customerAccount.isEmpty()) {
			return null;
		}
		List<Customer> customerResult= customerDao.getCustomerByAccount(customerAccount);
		if(customerResult== null || customerResult.isEmpty()) {
			return null;
		}
		
		return customerResult.get(0);
	}
	
	/**
	 * Get a Customer by Account and Password if dont exists return null
	 * @param customerAccount
	 * @param password
	 * @return
	 */
	@Transactional(readOnly = true)
	public Customer getCustomerByAccountAndPassword(String customerAccount,String password) {
		if(customerAccount == null || customerAccount.isEmpty() || 
				password == null || password.isEmpty()) {
			return null;
		}
		List<Customer> customerResult= customerDao.getCustomerByAccountAndPassword(customerAccount,password);
		if(customerResult== null || customerResult.isEmpty()) {
			return null;
		}
		
		return customerResult.get(0);
	}

	
	public List<Customer> getAll() {
		return   customerDao.findAll();
	}
}
