package com.ninja.rmm.ws.dto;

/**
 * DTO tha wrap a generic response of endpoints
 * @author CRIS
 *
 */
public class DefaultResponseDto {

	private String responseCode;
	
	private String responseDescription;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	
}
