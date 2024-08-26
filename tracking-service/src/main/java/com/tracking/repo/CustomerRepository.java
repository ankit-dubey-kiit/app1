package com.tracking.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tracking.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

	public Optional<Customer> findByCustomerRef(String customerRef);

}
