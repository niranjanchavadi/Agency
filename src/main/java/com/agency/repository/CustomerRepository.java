package com.agency.repository;

import com.agency.entitty.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository  extends MongoRepository<Customer, String> {
}
