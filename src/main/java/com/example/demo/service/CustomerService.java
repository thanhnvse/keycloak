package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllUsers();
    Customer saveCustomer(Customer customer);
    void addUser(User user);
}
