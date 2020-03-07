package com.intercom.msf.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerDeserializerTest {

    private Customer customer;

    @BeforeEach
    void setUp(){
        Coordinates coordinates = new Coordinates(53.761389, -7.2875);
        customer = new Customer(30, "Nick Enright", coordinates);
    }

    @Test
    @DisplayName("Sucess on Serialize")
    void shouldReturnSuccessOnSerialize(){
        String json = "{\"latitude\": \"53.761389\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
        Customer customerGson = gson.fromJson(json, Customer.class);
        assertEquals(customer,customerGson);
    }

    @Test
    @DisplayName("Throws exception on serialize")
    void shouldThrowsGenericException(){
        String json = "{\"latitude\": \"\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
        assertThrows(Exception.class, () -> gson.fromJson(json, Customer.class));
    }

    @Test
    @DisplayName("Throws number format exception on serialize")
    void shouldThrowsNumberException(){
        String json = "{\"latitude\": \"\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerDeserializer()).create();
        assertThrows(NumberFormatException.class, () -> gson.fromJson(json, Customer.class));
    }

    @AfterEach
    void tearDown(){
        customer = null;
    }

}