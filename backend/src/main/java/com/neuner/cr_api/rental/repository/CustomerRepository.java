package com.neuner.cr_api.rental.repository;

import com.neuner.cr_api.rental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>  {
}
