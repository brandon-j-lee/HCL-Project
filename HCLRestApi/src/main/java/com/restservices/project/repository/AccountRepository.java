package com.restservices.project.repository;

import com.restservices.project.beans.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	Optional<Account> findById(int id);
}