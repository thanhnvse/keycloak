package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        addCustomers();
        Iterable<Customer> customers = customerService.getAllUsers();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {

        Customer customer1 = new Customer();
        customer1.setAddress("1111 foo blvd");
        customer1.setName("Foo Industries");
        customer1.setServiceRendered("Important services");
        customerService.saveCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("2222 bar street");
        customer2.setName("Bar LLP");
        customer2.setServiceRendered("Important services");
        customerService.saveCustomer(customer2);

        Customer customer3 = new Customer();
        customer3.setAddress("33 main street");
        customer3.setName("Big LLC");
        customer3.setServiceRendered("Important services");
        customerService.saveCustomer(customer3);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        customerService.addUser(user);
    }
}
