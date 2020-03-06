package com.intercom.msf.service;

import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matheus on 3/5/20.
 */
class InviteServiceTest {

    private int distance;
    private List<Customer> customerExpected;

    @BeforeEach
    void setUp() {
        distance = 100;
        Coordinates co1 = new Coordinates(51.8856167, -10.4240951);
        Customer c1 = new Customer(2, "Ian McArdle", co1);

        Coordinates co2 = new Coordinates(53.1302756, -6.2397222);
        Customer c2 = new Customer(5, "Nora Dempsey", co2);

        Coordinates co3 = new Coordinates(51.92893, -10.27699);
        Customer c3 = new Customer(1, "Alice Cahill", co3);

        Coordinates co4 = new Coordinates(53.2451022, -6.238335);
        Customer c4 = new Customer(4, "Ian Kehoe", co4);

        Coordinates co5 = new Coordinates(52.3191841, -8.5072391);
        Customer c5 = new Customer(3, "Jack Enright", co5);

        customerExpected = Arrays.asList(c1, c2, c3, c4, c5);
    }

    @Test
    void shouldReturnFiveOrderCustomers() {
        assertNotNull(InviteService.findSortedCustomerWithin(distance, customerExpected));
    }

    @Test
    void shouldReturnTwoOrderedCustomers(){
        assertEquals(2,InviteService.findSortedCustomerWithin(distance, customerExpected).size());
    }

    @Test
    void shouldReturnEmptyCustomers(){
    }

    @Test
    void shouldValidateCustomerSavedAscending(){

    }

    @Test
    void shouldReturnDifferenceZero(){
        assertEquals(0,0);
    }

    @Test
    void shouldReturnMoreThanHundred(){
        assertEquals(101, 0);
    }

    @AfterEach
    void tearDown() {
    }

}