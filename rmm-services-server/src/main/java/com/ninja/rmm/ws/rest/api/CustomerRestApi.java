package com.ninja.rmm.ws.rest.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.bussines.services.CustomerBussinesServ;
import com.ninja.rmm.bussines.services.CustomerServiceBussinesServ;
import com.ninja.rmm.bussines.services.DeviceBussinesServ;
import com.ninja.rmm.bussines.services.ServiceBussinesServ;
import com.ninja.rmm.enums.CodeResponseEnum;
import com.ninja.rmm.jpa.entity.Customer;
import com.ninja.rmm.jpa.entity.CustomerService;
import com.ninja.rmm.jpa.entity.Device;
import com.ninja.rmm.jpa.entity.Service;
import com.ninja.rmm.ws.dto.DefaultResponseDto;
import com.ninja.rmm.ws.dto.DetailCostResponseDto;
import com.ninja.rmm.ws.dto.TotalMonthlyCostRequestDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Rest controller for ADD, GET, LOGIN and GET TOTAL MONTHLY COST of a Customer
 * @author CRIS
 *
 */
@RestController
public class CustomerRestApi {

	@Autowired
	CustomerBussinesServ customerService;

	@Autowired
	DeviceBussinesServ deviceService;

	@Autowired
	CustomerServiceBussinesServ serviceCustomerService;

	@Autowired
	ServiceBussinesServ serviceService;

	private final String SECRET_KEY = "NinjaRmm";

	private static final String SERVICE_DEVICE_REGISTER = "DeviceCost";

	/**
	 * Endpoint for get all customers, dont need token for use
	 * @return
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<Customer> getCustomer() {
		return customerService.getAll();
	}

	/**
	 * Endpoint for Login a customer, dont need token for use
	 * @param customerAccount
	 * @param customerPassword
	 * @return
	 */
	@RequestMapping(value = "/customerLogin", method = RequestMethod.POST)
	public Customer login(@RequestParam String customerAccount, @RequestParam String customerPassword) {
		Customer customer = customerService.getCustomerByAccountAndPassword(customerAccount, customerPassword);
		//if customer exists, generate a token
		if (customer != null) {
			customer.setToken(getJWTToken(customerAccount));
		}
		return customer;

	}

	/**
	 * Private Method for get Token by customerAccount
	 * @param customerAccount
	 * @return
	 */
	private String getJWTToken(String customerAccount) {

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		//Generated Token by CustomerAccount subject, and secretKey
		String token = Jwts.builder().setId("rmmAPI").setSubject(customerAccount)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes()).compact();

		return "Bearer " + token;

	}

	/**
	 * Endpoint for save a new Customer, dont need token for use
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public DefaultResponseDto saveCustomer(@RequestBody Customer customer) {
		DefaultResponseDto response = new DefaultResponseDto();
		customerService.save(customer);
		Service serviceDevice = serviceService.getServiceByName(SERVICE_DEVICE_REGISTER).get(0);
		CustomerService serviceCustomer = new CustomerService();

		serviceCustomer.setServiceId(serviceDevice);
		serviceCustomer.setCustomerId(customer);
		serviceCustomerService.save(serviceCustomer);
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Customer saved succesfull ");
		return response;
	}

	/**
	 * Endpoint for get total monthly cost by customerAccount, need Token for use
	 * @param customerAccount
	 * @return
	 */
	@RequestMapping(value = "/totalMonthlyCost", method = RequestMethod.GET)
	public TotalMonthlyCostRequestDto getTotalMonthlyCostByCustomer(@RequestParam String customerAccount) {
		TotalMonthlyCostRequestDto response = new TotalMonthlyCostRequestDto();
		//Get all devices of customer
		List<Device> devices = deviceService.getDeviceByCustomer(customerAccount);
		Integer totalDevices = devices.size();
		//Get all services of customer
		List<CustomerService> services = serviceCustomerService.getServicesByCustomer(customerAccount);
		List<DetailCostResponseDto> detailList = new ArrayList<DetailCostResponseDto>();
		Map<String, BigDecimal> costsMap = new HashMap<String, BigDecimal>();
		//Iterate each service by name and put in a costsMap
		for (CustomerService service : services) {
			//if service dont get a type device defined, put in a costsMap
			if (service.getServiceId().getDeviceTypeId() == null) {
				costsMap.put(service.getServiceId().getServiceName(),
						service.getServiceId().getServicePrice().multiply(new BigDecimal(totalDevices)));
			} else {
				//get total device by type device
				Long deviceOs = devices.stream().filter(
						d -> d.getDeviceTypeId().getDeviceTypeId().equals(service.getServiceId().getDeviceTypeId()))
						.count();
				if (!costsMap.containsKey(service.getServiceId().getServiceName())) {
					costsMap.put(service.getServiceId().getServiceName(),
							service.getServiceId().getServicePrice().multiply(new BigDecimal(deviceOs)));
				} else {
					BigDecimal totalDetail = costsMap.get(service.getServiceId().getServiceName());
					costsMap.put(service.getServiceId().getServiceName(), totalDetail
							.add(service.getServiceId().getServicePrice().multiply(new BigDecimal(deviceOs))));
				}
			}
		}
		BigDecimal totalCost = BigDecimal.ZERO;
		//Iterate Map for fill a list of detailCost
		for (String key : costsMap.keySet()) {
			DetailCostResponseDto cost = new DetailCostResponseDto();
			cost.setNameService(key);
			cost.setCostService(costsMap.get(key));
			totalCost = totalCost.add(costsMap.get(key));
			detailList.add(cost);
		}
		response.setTotalCost(totalCost);
		response.setDetailCosts(detailList);
		return response;
	}
}
