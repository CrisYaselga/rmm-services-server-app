package com.ninja.rmm.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.jpa.entity.Device;

/**
 * Repository for opperations in Device Entity
 * @author CRIS
 *
 */
public interface DeviceDao extends JpaRepository<Device, Integer>{

	@Query("SELECT a FROM Device a WHERE a.customerId.customerAccount = ?1")
	List<Device> getDeviceByCustomer(String customerAccount);
}
