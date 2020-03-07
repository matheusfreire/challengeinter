package com.intercom.msf.service;

import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matheus on 3/5/20.
 */
class CustomersServiceTest {

    private int distance;
    private List<Customer> customerExpected;
    private List<Customer> customerMoreThan;
    private Set<Customer> setOfCustomer;

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
        setOfCustomer = new TreeSet<>(Arrays.asList(c4,c2));

        Coordinates coo1 = new Coordinates(-15.77972, -47.92972);
        Customer customerA = new Customer(2, "Ian McArdle", coo1);

        Coordinates coo2 = new Coordinates(-15.77972, -47.92972);
        Customer customerB = new Customer(5, "Nora Dempsey", coo2);

        Coordinates coo3 = new Coordinates(-15.77972, -47.92972);
        Customer customerC = new Customer(1, "Alice Cahill", coo3);

        Coordinates coo4 = new Coordinates(-15.77972, -47.92972);
        Customer customerD = new Customer(4, "Ian Kehoe", coo4);

        Coordinates coo5 = new Coordinates(-15.77972, -47.92972);
        Customer customerE = new Customer(3, "Jack Enright", coo5);
        customerMoreThan = Arrays.asList(customerA, customerB, customerC, customerD, customerE);
    }

    @Test
    void shouldReturnFiveOrderCustomers() {
        assertNotNull(CustomersService.findSortedCustomerWithin(distance, customerExpected));
    }

    @Test
    void shouldReturnTwoOrderedCustomers(){
        assertEquals(2, CustomersService.findSortedCustomerWithin(distance, customerExpected).size());
    }

    @Test
    void shouldReturnEmptyCustomers(){
        assertEquals(0, CustomersService.findSortedCustomerWithin(distance, customerMoreThan).size());
    }

    @Test
    void shouldValidateCustomerSavedAscending(){
        assertEquals(setOfCustomer, CustomersService.findSortedCustomerWithin(distance, customerExpected));
    }

    @AfterEach
    void tearDown() {
        customerExpected = null;
        customerMoreThan = null;
    }

}