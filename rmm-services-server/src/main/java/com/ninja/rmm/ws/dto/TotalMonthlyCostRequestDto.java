package com.ninja.rmm.ws.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Dto that wrap header response of totalMonthlyCost endpoint
 * @author CRIS
 *
 */
public class TotalMonthlyCostRequestDto {

	private BigDecimal totalCost;
	private List<DetailCostResponseDto> detailCosts;
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public List<DetailCostResponseDto> getDetailCosts() {
		return detailCosts;
	}
	public void setDetailCosts(List<DetailCostResponseDto> detailCosts) {
		this.detailCosts = detailCosts;
	}
	
}
