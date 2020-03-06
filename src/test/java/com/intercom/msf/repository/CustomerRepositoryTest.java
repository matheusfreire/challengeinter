package com.intercom.msf.repository;

import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by matheus on 3/5/20.
 */
class CustomerRepositoryTest {

    private static final String PATH_FILE_INPUT = "src/test/resources/input/%s";


    @Test
    void shouldTestCouldFindCustomerInFile() {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_five.txt"));
        assertNotNull(customersInFile);
    }

    @Test
    void shouldFindFiveCustomers() {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_five.txt"));
        assertEquals(5,customersInFile.size());
    }

    @Test
    void shouldFindTwoCustomers() {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_two.txt"));
        assertEquals(2,customersInFile.size());
    }

    @Test
    void shouldNotFindCustomers() {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_zero.txt"));
        assertEquals(0,customersInFile.size());
    }

    @Test
    void shouldThrowExceptionWhenFileDoesntExists() {
        assertThrows(NoSuchFileException.class, () -> CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "file_not_exists.txt")));
    }

    @Test
    void shouldThrowExceptionWhenContentIsNotValid(){
        assertThrows(Exception.class, () -> CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "file_invalid.txt")));
    }


    @Test
    void saveCustomersInvited() {
        assertDoesNotThrow(() -> CustomerRepository.saveCustomersInvited(null));
    }

    @Test
    void shouldValidateFileWasSaved(){
    }

    @Test
    void shouldValidateFileWasNotSaved(){
    }

    @Test
    void shouldValidateIfOneCustomerByLine(){
    }

    @Test
    void shouldValidateIfMoreThanOneByLine(){

    }

    @AfterEach
    void tearDown(){
    }

}