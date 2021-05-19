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
 * Entity than Maping a CUSTOMER_SERVICE table
 * @author CRIS
 *
 */
@Entity
@Table(name="CUSTOMER_SERVICE", schema="public")
public class CustomerService  implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="customer_service_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerServiceId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="service_id")
	private Service serviceId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;

	public Integer getCustomerServiceId() {
		return customerServiceId;
	}

	public void setCustomerServiceId(Integer customerServiceId) {
		this.customerServiceId = customerServiceId;
	}

	public Service getServiceId() {
		return serviceId;
	}

	public void setServiceId(Service serviceId) {
		this.serviceId = serviceId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	
}
