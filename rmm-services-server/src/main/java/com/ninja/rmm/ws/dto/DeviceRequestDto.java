package com.ninja.rmm.ws.dto;

/**
 * DTO that wrap request of add device endpoint
 * @author CRIS
 *
 */
public class DeviceRequestDto {

	private String systemName;
	
	private String customerAccount;
	
	private String deviceType;
	
	private Integer deviceId;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

}
