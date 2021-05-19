package com.ninja.rmm.ws.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.bussines.services.CustomerBussinesServ;
import com.ninja.rmm.bussines.services.DeviceBussinesServ;
import com.ninja.rmm.bussines.services.DeviceTypeBussinesServ;
import com.ninja.rmm.enums.CodeResponseEnum;
import com.ninja.rmm.jpa.entity.Customer;
import com.ninja.rmm.jpa.entity.Device;
import com.ninja.rmm.jpa.entity.DeviceType;
import com.ninja.rmm.ws.dto.DefaultResponseDto;
import com.ninja.rmm.ws.dto.DeviceRequestDto;

/**
 * RestController for ADD,DELETE,MODIFY and GET Devices
 * @author CRIS
 *
 */
@RestController
public class DeviceRestApi {

	@Autowired
	DeviceBussinesServ deviceService;
	
	@Autowired
	CustomerBussinesServ customerService;

	@Autowired
	DeviceTypeBussinesServ deviceTypeService;
	
	/**
	 * Endpoitn for get devices by customerAccount, need a token for user
	 * @param customerAccount
	 * @return
	 */
	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public List<Device> getDeviceByCustomer(@RequestParam String customerAccount) {
		return deviceService.getDeviceByCustomer(customerAccount);
	}
	
	/**
	 * Endpoint for add a new device to customerAccount, need a token for user
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/device", method = RequestMethod.POST)
	public DefaultResponseDto saveDevice(@RequestBody DeviceRequestDto device) {
		DefaultResponseDto response = new DefaultResponseDto();
		Device deviceEntity= new Device();
		DeviceType type = deviceTypeService.getDeviceTypeBydeviceType(device.getDeviceType());
		if(type == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Device Type "+device.getDeviceType());
			return response;
		}
		Customer customer = customerService.getCustomerByAccount(device.getCustomerAccount());
		if(customer == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Customer Account "+device.getCustomerAccount());
			return response;
		}
		deviceEntity.setDeviceTypeId(type);
		deviceEntity.setCustomerId(customer);
		deviceEntity.setSystemName(device.getSystemName());
		deviceService.save(deviceEntity);
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Device saved succesful ");
		return response;
	}
	
	/**
	 * Endpoint for Modify a device of customer, need a token for user
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/device", method = RequestMethod.PUT)
	public DefaultResponseDto updateDevice(@RequestBody DeviceRequestDto device) {
		DefaultResponseDto response = new DefaultResponseDto();
		Device deviceEntity= deviceService.getDeviceById(device.getDeviceId());
		if(deviceEntity == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Device whit ID: "+device.getDeviceId());
			return response;
		}
		DeviceType type = deviceTypeService.getDeviceTypeBydeviceType(device.getDeviceType());
		if(type == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Device Type "+device.getDeviceType());
			return response;
		}
		Customer customer = customerService.getCustomerByAccount(device.getCustomerAccount());
		if(customer == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Customer Account "+device.getCustomerAccount());
			return response;
		}
		if(!device.getCustomerAccount().equals(customer.getCustomerAccount())) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Do not own the selected device  ");
			return response;
		}
		deviceEntity.setDeviceTypeId(type);
		deviceEntity.setCustomerId(customer);
		deviceEntity.setSystemName(device.getSystemName());
		
		deviceService.save(deviceEntity);
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Device saved succesful ");
		return response;
	}
	
	/**
	 * Endpoint for delete a device, need a token for user
	 * @param idDevice
	 * @return
	 */
	@RequestMapping(value = "/device", method = RequestMethod.DELETE)
	public DefaultResponseDto deleteDevice(@RequestParam Integer idDevice) {
		DefaultResponseDto response = new DefaultResponseDto();
		Device deviceEntity= deviceService.getDeviceById(idDevice);
		if(deviceEntity == null) {
			response.setResponseCode(CodeResponseEnum.ERROR.getCodeResponse());
			response.setResponseDescription("Not exist Device whit ID: "+idDevice);
			return response;
		}
		deviceService.delete(idDevice);
		response.setResponseCode(CodeResponseEnum.OK.getCodeResponse());
		response.setResponseDescription("Device deleted succesful ");
		return response;
	}
}
