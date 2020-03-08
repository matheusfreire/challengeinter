package com.intercom.msf;


import com.intercom.msf.model.Customer;
import com.intercom.msf.repository.CustomerRepository;
import com.intercom.msf.service.CustomersService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class App {

    private static final String FILE_PATH = "src/main/resources/%s";

    public static void main(String[] args) {
        try {
            String filePath = String.format(FILE_PATH, "input/customers.txt");
            List<Customer> customers = CustomerRepository.getCustomersFromFile(filePath);
            Set<Customer> sortedCustomerWithin = CustomersService.findSortedCustomerWithin(100, customers);
            String filePathOutput = String.format(FILE_PATH, "output/output.txt");
            CustomerRepository.saveCustomersInvited(sortedCustomerWithin, filePathOutput);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            System.out.println("File path should not be null or empty");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There's a problem with you file");
        }

    }
}
