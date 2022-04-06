package com.restservices.project;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@PostMapping
	public String createAccount()
	{
		return "http POST request was sent";
	}
	
	@GetMapping(path="/{accountID}")
	public String getAccount(@PathVariable String accountID)
	{
		return "http GET request was sent for account ID:"+accountID;
	}
	
	@PutMapping
	public String updateAccount()
	{
		return "http UPDATE request was sent";
	}
	
	@DeleteMapping
	public String deleteAccount()
	{
		return "http DELETE request was sent";
	}
}
