package com.intercom.msf;


import com.intercom.msf.model.Customer;
import com.intercom.msf.repository.CustomerRepository;
import com.intercom.msf.service.InviteService;

import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        String filePath = App.class.getClassLoader().getResource("input/customers.txt").getPath();
        List<Customer> customers = CustomerRepository.getCustomersFromFile(filePath);

        Set<Customer> sortedCustomerWithin = InviteService.findSortedCustomerWithin(100, customers);
        CustomerRepository.saveCustomersInvited(sortedCustomerWithin);
        System.out.println("Hello World!");
    }
}
