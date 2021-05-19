package com.ninja.rmm.bussines.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninja.rmm.jpa.dao.CustomerServiceDao;
import com.ninja.rmm.jpa.entity.CustomerService;

/**
 *  Transactional Service to access the to transacctions of CustomerServiceDao
 * @author CRIS
 *
 */
@Service
@Transactional
public class CustomerServiceBussinesServ {
	
	@Autowired
	CustomerServiceDao customerServiceDao;

	public void save(CustomerService customerServ) {
		customerServiceDao.save(customerServ);
	}
	
	@Transactional(readOnly = true)
	public List<CustomerService> getServicesByCustomer(String customerAccount) {
		return   customerServiceDao.getServicesByCustomer(customerAccount);
	}
	
	@Transactional(readOnly = true)
	public List<CustomerService> getServicesByCustomerAndService(String customerAccount, String serviceName) {
		return  customerServiceDao.getServicesByCustomerAndService(customerAccount,serviceName);
	}
	
	public void delete(Integer idCustomerService) {
		customerServiceDao.deleteById(idCustomerService);
	}
	
	@Transactional(readOnly = true)
	public CustomerService getById(Integer idCustomerService) {
		Optional<CustomerService> response=customerServiceDao.findById(idCustomerService);
		return   response.isPresent() ? response.get():null ;
	}
}
