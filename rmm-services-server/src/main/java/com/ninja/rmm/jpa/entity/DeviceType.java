package com.ninja.rmm.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity than Maping a DEVICE_TYPE table
 * @author CRIS
 *
 */
@Entity
@Table(name="DEVICE_TYPE", schema="public")
public class DeviceType implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="device_type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer deviceTypeId;

	@Column(name="device_type")
	private String deviceType;

	@Column(name="operating_system")
	private String operatingSystem;

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

}
