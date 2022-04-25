package com.restservices.project.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.restservices.project.beans.Account;
import com.restservices.project.exceptions.AccountExistsException;
import com.restservices.project.exceptions.KeyNotFoundException;
import com.restservices.project.repository.AccountRepository;
import com.restservices.project.vo.AccountVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AccountService {
	
	AccountRepository accountRepository;

	private static final Logger logger = LogManager.getLogger(AccountService.class);
	
	public boolean create(AccountVO accountVO) {
		int id = accountVO.getId();
		String name = accountVO.getName();
		String pass = accountVO.getPass();
		String info = accountVO.getInfo();
		Optional<Account> existingAccount = accountRepository.findById(id);
		if (existingAccount.isPresent()) {
			throw new AccountExistsException("Account with ID " + id + " already exists");
		}
		Account account = new Account(id, name, pass, info);
		BeanUtils.copyProperties(accountVO, account);
		try {
			accountRepository.save(account);
		} catch (DataAccessException dae) {
			logger.error("Error while saving into database :{}", dae.getMessage());
		}
		return true;
	}
	
	public boolean delete(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent()) {
			throw new KeyNotFoundException("No account with ID " + id + " found");
		}
		try {
			accountRepository.deleteById(id);
		} catch (DataAccessException dae) {
			logger.error("Error while saving into database :{}", dae.getMessage());
		}
		return true;
	}
	
	public boolean update(AccountVO accountVO, int id) {
		Optional<Account> existingAccount = accountRepository.findById(id);
		if (!existingAccount.isPresent()) {
			throw new KeyNotFoundException("No account with ID " + id + " found");
		}
		Account account = existingAccount.get();
		account.setName(accountVO.getName());
		account.setPass(accountVO.getPass());
		account.setInfo(accountVO.getInfo());
		try {
			accountRepository.save(account);
		} catch (DataAccessException dae) {
			logger.error("Error while saving into database :{}", dae.getMessage());
		}
		return true;
	}
	public AccountVO get(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent()) {
			throw new KeyNotFoundException("No account with ID" + id + " found");
		}
		AccountVO accountVO = new AccountVO();
		BeanUtils.copyProperties(account.get(), accountVO);
		return accountVO;
	}
}