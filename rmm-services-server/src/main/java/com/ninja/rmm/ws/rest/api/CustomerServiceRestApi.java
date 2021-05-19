package com.ninja.rmm.ws.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.bussines.services.CustomerBussinesServ;
import com.ninja.rmm.bussines.services.CustomerServiceBussinesServ;
import com.ninja.rmm.bussines.services.ServiceBussinesServ;
import com.ninja.rmm.enums.CodeResponseEnum;
import com.ninja.rmm.jpa.entity.Customer;
import com.ninja.rmm.jpa.entity.CustomerService;
import com.ninja.rmm.jpa.entity.Service;
import com.ninja.rmm.ws.dto.CustomerServiceDto;
import com.ninja.rmm.ws.dto.DefaultResponseDto;

/**
 * Rest Controller for ADD, DELETE and GET Services of customer
 * @author CRIS
 *
 */
@RestController
public class CustomerServiceRestApi {

	@Autowired
	CustomerBussinesServ customerService;

	@Autowired
	CustomerServiceBussinesServ serviceCustomerService;

	@Autowired
	ServiceBussinesServ serviceService;

	/**
	 * Endpoint for get services by customerAccount, need a token for use
	 * @param customerAccount
	 * @return
	 */
	@RequestMapping(value = "/customerService", method = RequestMethod.GET)
	public List<CustomerService> getCustomerServices(@RequestParam String customerAccount) {
		return serviceCustomerService.getServicesByCustomer(customerAccount);
	}

	/**
	 * Endpoitn for add a new service of customer, need a token for use
	 * @param customerServ
	 * @return
	 */
	@RequestMapping(value = "/customerService", method = RequestMethod.POST)
	public DefaultResponseDto addCustomerService(@RequestBody CustomerServiceDto customerServ) {
		DefaultResponseDto response = new DefaultResponseDto();

		List<CustomerService> customerServices = serviceCustomerService
				.getServicesByCustomerAndService(customerServ.getCustomerAccount(), customerServ.getServiceName());
		if (customerServices != null && !customerServices.isEmpty()) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Customer Service is already added ");
			return response;
		}

		List<Service> services = serviceService.getServiceByName(customerServ.getServiceName());
		if (services == null || services.isEmpty()) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Service " + customerServ.getServiceName());
			return response;
		}
		Customer customer = customerService.getCustomerByAccount(customerServ.getCustomerAccount());
		if (customer == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Customer Account " + customerServ.getCustomerAccount());
			return response;
		}
		for (Service service : services) {
			CustomerService serviceCustomer = new CustomerService();
			serviceCustomer.setServiceId(service);
			serviceCustomer.setCustomerId(customer);
			serviceCustomerService.save(serviceCustomer);
		}
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Customer service added succesfull ");
		return response;
	}

	/**
	 * Endpoint for delete service of customer, need a token for use
	 * @param customerAccount
	 * @param nameService
	 * @return
	 */
	@RequestMapping(value = "/customerService", method = RequestMethod.DELETE)
	public DefaultResponseDto deleteDevice(@RequestParam String customerAccount, @RequestParam String nameService) {
		DefaultResponseDto response = new DefaultResponseDto();
		List<CustomerService> customerServices = serviceCustomerService.getServicesByCustomerAndService(customerAccount,
				nameService);
		if (customerServices == null || customerServices.isEmpty()) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Customer Service ");
			return response;
		}
		customerServices.stream().forEach(s -> serviceCustomerService.delete(s.getCustomerServiceId()));
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Customer Service deleted succesful ");
		return response;
	}
}
