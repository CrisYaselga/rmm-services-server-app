package com.ninja.rmm.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity than Maping a CUSTOMER table
 * @author CRIS
 *
 */
@Entity
@Table(name="CUSTOMER", schema="public")
public class Customer implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;

	@Column(name="customer_account", unique=true)
	private String customerAccount;

	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="customer_password")
	private String customerPassword;
	
	@Transient
	private String token;

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
