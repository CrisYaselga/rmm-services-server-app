package com.ninja.rmm.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.jpa.entity.DeviceType;

/**
 * Repository for opperations in DeviceType Entity
 * @author CRIS
 *
 */
public interface DeviceTypeDao extends JpaRepository<DeviceType, Integer>{

	@Query("SELECT a FROM DeviceType a WHERE a.deviceType = ?1")
	List<DeviceType> getDeviceTypeBydeviceType(String deviceType);
}
