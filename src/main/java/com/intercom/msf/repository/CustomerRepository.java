package com.intercom.msf.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.msf.deserializer.CustomerDeserializer;
import com.intercom.msf.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by matheus on 3/5/20.
 */
public class CustomerRepository {

    public static List<Customer> getCustomersFromFile(String filePath) {
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
            Path path = Paths.get(filePath);
            Stream<String> lines = Files.lines(path);
            return lines.map(line -> gson.fromJson(line, Customer.class)).collect(Collectors.toList());
        } catch (IOException ne) {
            ne.printStackTrace();
        }
        return null;
    }

    public static void saveCustomersInvited(Set<Customer> sortedCustomerWithin) {
    }
}
