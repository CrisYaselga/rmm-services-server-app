package com.ninja.rmm.ws.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.bussines.services.ServiceBussinesServ;
import com.ninja.rmm.jpa.entity.Service;

/**
 * RestController for GET ALL services
 * @author CRIS
 *
 */
@RestController
public class ServiceRestApi {

	@Autowired
	ServiceBussinesServ serviceService;


	/**
	 * Endpoint for get all Services, dont need token for use
	 * @return
	 */
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public List<Service> getServices() {
		return serviceService.getAll();
	}

}
