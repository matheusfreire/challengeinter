package com.intercom.msf;


import com.intercom.msf.model.Customer;
import com.intercom.msf.repository.CustomerRepository;
import com.intercom.msf.service.CustomersService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        try {
            String filePath = App.class.getClassLoader().getResource("input/customers.txt").getPath();
            List<Customer> customers = CustomerRepository.getCustomersFromFile(filePath);
            Set<Customer> sortedCustomerWithin = CustomersService.findSortedCustomerWithin(100, customers);
            String filePathOutput = App.class.getClassLoader().getResource("output/output.txt").getPath();
            CustomerRepository.saveCustomersInvited(sortedCustomerWithin, filePathOutput);
        } catch (NullPointerException ne) {
            System.out.println("File path should not be null or empty");
        } catch (Exception e) {
            System.out.println("There's a problem with you file");
        }

    }
}
