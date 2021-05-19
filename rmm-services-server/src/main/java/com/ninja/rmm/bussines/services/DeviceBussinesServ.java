package com.ninja.rmm.bussines.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninja.rmm.jpa.dao.DeviceDao;
import com.ninja.rmm.jpa.entity.Device;

/**
 * Transactional Service to access the to transacctions of DeviceDao
 * @author CRIS
 *
 */
@Service
@Transactional
public class DeviceBussinesServ {
	
	@Autowired
	DeviceDao deviceDao;

	public void save(Device device) {
		deviceDao.save(device);
	}
	
	public void delete(Integer idDevice) {
		deviceDao.deleteById(idDevice);
	}
	
	@Transactional(readOnly = true)
	public List<Device> getDeviceByCustomer(String customerAccount) {
		return   deviceDao.getDeviceByCustomer(customerAccount);
	}
	
	@Transactional(readOnly = true)
	public Device getDeviceById(Integer idDevice) {
		Optional<Device> response=deviceDao.findById(idDevice);
		return   response.isPresent() ? response.get():null ;
	}
}
