package com.ninja.rmm.ws.dto;

import java.math.BigDecimal;

/**
 * Dto that wrap detail response of totalMonthlyCost endpoint
 * @author CRIS
 *
 */
public class DetailCostResponseDto {

	private String nameService;
	
	private BigDecimal costService;

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public BigDecimal getCostService() {
		return costService;
	}

	public void setCostService(BigDecimal costService) {
		this.costService = costService;
	}	
}
