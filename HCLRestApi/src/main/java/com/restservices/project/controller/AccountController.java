package com.restservices.project.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.project.services.AccountService;
import com.restservices.project.vo.AccountVO;

@Validated
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@PostMapping(name = "Create Account", value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAccount(@RequestBody AccountVO accountVO)
	{
		return new ResponseEntity<>(accountService.create(accountVO), HttpStatus.OK);
	}
	
	@GetMapping(name = "Get Account", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAccount(@PathVariable @Positive(message = "Invalid ID") int id)
	{
		return new ResponseEntity<>(accountService.get(id), HttpStatus.OK);
	}
	
	@PutMapping(name = "Update Account", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateAccount(@PathVariable @Positive(message = "Invalid ID") int id, @RequestBody @Valid AccountVO accountVO)
	{
		AccountVO existingAccount = accountService.get(id);
		return new ResponseEntity<>(accountService.update(existingAccount, id), HttpStatus.OK);
	}
	
	@DeleteMapping(name = "Delete Account", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAccount(@PathVariable @Positive(message = "Invalid ID") int id)
	{
		return new ResponseEntity<>(accountService.delete(id), HttpStatus.OK);
	}
}
