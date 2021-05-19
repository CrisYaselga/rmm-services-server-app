package com.ninja.rmm.enums;

/**
 * Enum whit code response 
 * OK is 0
 * ERROR is 1
 * @author CRIS
 *
 */
public enum CodeResponseEnum {
	OK("0"), ERROR("1");

	private String codeResponse;

	public String getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}

	private CodeResponseEnum(String codeResponse) {
		this.codeResponse = codeResponse;
	}

}
