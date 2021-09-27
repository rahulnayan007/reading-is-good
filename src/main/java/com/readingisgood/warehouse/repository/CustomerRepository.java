package com.readingisgood.warehouse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.warehouse.entity.Customer;

/**
 * 
 * @author rahul
 *
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
