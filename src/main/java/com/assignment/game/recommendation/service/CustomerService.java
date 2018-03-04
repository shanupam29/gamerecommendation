package com.assignment.game.recommendation.service;

import com.assignment.game.recommendation.entity.Customer;
import com.assignment.game.recommendation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Service layer to fetch the games recommendations for a given customer id
     * from the repository.
     *
     * @param id
     * @return Customer
     */
    public Customer findGamesById(Long id) {
        return customerRepository.findGamesById(id);
    }

    /**
     * Service layer to persist the customer entity into the
     * repository.
     *
     * @param customerList
     * @return List<Customer>
     */
    public List<Customer> saveCustomers(List<Customer> customerList){
        return customerRepository.saveAll(customerList);
    }
}
