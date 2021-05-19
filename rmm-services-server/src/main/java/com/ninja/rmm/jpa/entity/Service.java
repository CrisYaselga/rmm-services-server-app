package com.ninja.rmm.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity than Maping a SERVICE table
 * @author CRIS
 *
 */
@Entity
@Table(name="SERVICE", schema="public")
public class Service implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="service_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serviceId;

	@Column(name="service_name")
	private String serviceName;

	@Column(name="service_description")
	private String serviceDescription;

	@Column(name="service_price")
	private BigDecimal servicePrice;

	@Column(name="device_type_id")
	private Integer deviceTypeId;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

}
