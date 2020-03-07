package com.intercom.msf.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.msf.deserializer.CustomerDeserializer;
import com.intercom.msf.model.Customer;

import java.io.*;
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

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static List<Customer> getCustomersFromFile(String filePath) throws NullPointerException, IOException {
        if(filePath != null && !filePath.isEmpty()){
            Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
            Path path = Paths.get(filePath);
            Stream<String> lines = Files.lines(path);
            return lines.map(line -> gson.fromJson(line, Customer.class)).collect(Collectors.toList());
        } else {
            throw new NullPointerException("Filepath couldn't be null ");
        }
    }

    public static void saveCustomersInvited(Set<Customer> sortedCustomerWithin, String filePath) throws NullPointerException, IOException, Exception {
        if(filePath != null && !filePath.isEmpty()){
            if(sortedCustomerWithin == null || sortedCustomerWithin.isEmpty()){
                throw new Exception("Customers should be filled");
            }
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            Stream<String> stringStream = sortedCustomerWithin.stream().map(gson::toJson);
            writeFile(filePath,stringStream);
        } else {
            throw new NullPointerException("Filepath  couldn't be null ");
        }
    }

    private static void writeFile(String filePath,Stream<String> stringStream) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        stringStream.forEach(customerS -> {
            try {
                fileWriter.write(customerS + LINE_SEPARATOR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileWriter.close();
    }
}
