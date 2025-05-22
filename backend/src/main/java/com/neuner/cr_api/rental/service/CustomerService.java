package com.neuner.cr_api.rental.service;

import com.neuner.cr_api.rental.model.Customer;
import com.neuner.cr_api.rental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        log.info("Creating customer");
        customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        log.info("Reading customer");
        return customerRepository.findById(id).orElse(null);
    }

    public void updateCustomer(Long id, Customer customer) {
        log.info("Updating customer");
        customer.setCustomerId(id);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        log.info("Deleting customer");
        customerRepository.deleteById(id);
    }

    public List<Customer> getCustomers() {
        log.info("Reading customers");
        return customerRepository.findAll();
    }
}
