package com.ninja.rmm.bussines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninja.rmm.jpa.dao.DeviceTypeDao;
import com.ninja.rmm.jpa.entity.DeviceType;

/**
 * Transactional Service to access the to transacctions of DeviceTypeDao
 * @author CRIS
 *
 */
@Service
@Transactional
public class DeviceTypeBussinesServ {
	
	@Autowired
	DeviceTypeDao deviceTypeDao;

	/**
	 * Get a DeviceType by name of typeDevice if dont exists return null
	 * @param deviceType
	 * @return
	 */
	@Transactional(readOnly = true)
	public DeviceType getDeviceTypeBydeviceType(String deviceType) {
		if(deviceType == null || deviceType.isEmpty()) {
			return null;
		}
		List<DeviceType> deviceResult= deviceTypeDao.getDeviceTypeBydeviceType(deviceType);
		if(deviceResult== null || deviceResult.isEmpty()) {
			return null;
		}
		return deviceResult.get(0);
	}
}
