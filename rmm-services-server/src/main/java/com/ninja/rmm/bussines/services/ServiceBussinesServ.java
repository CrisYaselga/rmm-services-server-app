package com.ninja.rmm.bussines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ninja.rmm.jpa.dao.ServiceDao;
import com.ninja.rmm.jpa.entity.Service;

/**
 * Transactional Service to access the to transacctions of ServiceDao
 * @author CRIS
 *
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceBussinesServ {
	
	@Autowired
	ServiceDao serviceDao;

	@Transactional(readOnly = true)
	public List<Service> getServiceByName(String serviceName){
		return serviceDao.getServiceByName(serviceName);
	}
	
	@Transactional(readOnly = true)
	public List<Service> getAll(){
		return serviceDao.findAll();
	}
}
