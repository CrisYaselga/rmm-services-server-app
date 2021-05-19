package com.ninja.rmm.ws.dto;

/**
 * DTO that wrap request of CustomerService
 * @author CRIS
 *
 */
public class CustomerServiceDto {

	private String serviceName;
	
	private String customerAccount;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	
}
