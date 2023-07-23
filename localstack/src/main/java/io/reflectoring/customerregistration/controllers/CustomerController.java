/**
 * 
 */
package io.reflectoring.customerregistration.controllers;

import io.reflectoring.customerregistration.dtos.CustomerCreateResponse;
import io.reflectoring.customerregistration.dtos.CustomerDto;
import io.reflectoring.customerregistration.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.reflectoring.customerregistration.dtos.CustomerCreateRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pratik Das
 *
 */
@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/")
	@ResponseBody
	public CustomerCreateResponse registerCustomer(@RequestBody final CustomerCreateRequest request) {
		String customerID = customerService.createCustomer(request);
		return CustomerCreateResponse.builder()
		.customerID(customerID)
		.build();
		
	}
	
	@GetMapping("/{customerID}")
	@ResponseBody
	public CustomerDto getCustomerByID(@PathVariable("customerID") String customerID) {
		log.info("g=fetching customer with id {}", customerID);
		return customerService.fetchCustomer(customerID);
	}
	

}
