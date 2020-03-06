package com.intercom.msf.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDeserializerTest {

    private Customer customer;

    @BeforeEach
    void setUp(){
        Coordinates coordinates = new Coordinates(53.761389, -7.2875);
        customer = new Customer(30, "Nick Enright", coordinates);
    }

    @Test
    void shouldReturnSuccessOnSerialize(){
        String json = "{\"latitude\": \"53.761389\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
        Customer customerGson = gson.fromJson(json, Customer.class);
        assertEquals(customer,customerGson);
    }

    @AfterEach
    void tearDown(){
        customer = null;
    }

}