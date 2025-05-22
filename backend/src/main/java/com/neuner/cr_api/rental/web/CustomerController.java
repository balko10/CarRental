package com.neuner.cr_api.rental.web;

import com.neuner.cr_api.rental.model.Customer;
import com.neuner.cr_api.rental.service.CustomerService;
import com.neuner.cr_api.rental.web.dto.CustomerDto;
import com.neuner.cr_api.rental.web.dto.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customer) {
        log.info("Creating customer");
        Customer customerEntity = CustomerMapper.toEntity(customer);
        customerService.createCustomer(customerEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        log.info("Reading customer");
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        CustomerDto customerDto = CustomerMapper.toDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        log.info("Reading customers");
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDto> customerDtos = CustomerMapper.toDtoList(customers);
        return ResponseEntity.ok(customerDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customer) {
        log.info("Updating customer");
        Customer customerEntity = CustomerMapper.toEntity(customer);
        customerEntity.setCustomerId(id);
        customerService.updateCustomer(id, customerEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Deleting customer");
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
