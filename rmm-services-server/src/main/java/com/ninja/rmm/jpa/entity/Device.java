package com.ninja.rmm.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity than Maping a DEVICE table
 * @author CRIS
 *
 */
@Entity
@Table(name="DEVICE", schema="public")
public class Device implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="device_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer deviceId;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to DeviceType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="device_type_id")
	private DeviceType deviceTypeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public DeviceType getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(DeviceType deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

}
