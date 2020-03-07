package com.intercom.msf.repository;

import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by matheus on 3/5/20.
 */
class CustomerRepositoryTest {

    private static final String PATH_FILE_INPUT = "src/test/resources/input/%s";
    private static final String PATH_FILE_OUTPUT = "src/test/resources/output/%s";

    @Test
    void shouldTestCouldFindCustomerInFile() throws IOException {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_five.txt"));
        assertNotNull(customersInFile);
    }

    @Test
    void shouldFindFiveCustomers() throws IOException {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_five.txt"));
        assertEquals(5,customersInFile.size());
    }

    @Test
    void shouldFindTwoCustomers() throws IOException {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_two.txt"));
        assertEquals(2,customersInFile.size());
    }

    @Test
    void shouldNotFindCustomers() throws IOException {
        List<Customer> customersInFile = CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "customer_with_zero.txt"));
        assertEquals(0,customersInFile.size());
    }

    @Test
    @DisplayName("Throw Exception When File Doesn't exists")
    void shouldThrowExceptionWhenFileDoesntExists() {
        assertThrows(NoSuchFileException.class, () -> CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "file_not_exists.txt")));
    }

    @Test
    void shouldThrowExceptionWhenContentIsNotValid(){
        assertThrows(Exception.class, () -> CustomerRepository.getCustomersFromFile(String.format(PATH_FILE_INPUT, "file_invalid.txt")));
    }

    @Test
    void shouldThrowNullException(){
        assertThrows(NullPointerException.class, () -> CustomerRepository.getCustomersFromFile(""));
    }

    @Test
    void saveCustomersInvited() {
        assertDoesNotThrow(() -> CustomerRepository.saveCustomersInvited(null, null));
    }

    @Test
    void shouldValidateFileWasSaved() throws IOException {
        Coordinates co2 = new Coordinates(53.1302756, -6.2397222);
        Customer c2 = new Customer(5, "Nora Dempsey", co2);

        Coordinates co4 = new Coordinates(53.2451022, -6.238335);
        Customer c4 = new Customer(4, "Ian Kehoe", co4);

        Set<Customer> setOfCustomer = new TreeSet<>(Arrays.asList(c4,c2));
        CustomerRepository.saveCustomersInvited(setOfCustomer,String.format(PATH_FILE_OUTPUT, "output.txt"));

        Path path = Paths.get(String.format(PATH_FILE_OUTPUT, "output.txt"));
        assertTrue(Files.exists(path));
    }

    @Test
    void shouldValidateFileWasNotSaved() throws IOException {
        CustomerRepository.saveCustomersInvited(new TreeSet<>(),String.format(PATH_FILE_OUTPUT, "output.txt"));
        Path path = Paths.get(String.format(PATH_FILE_OUTPUT, "output.txt"));
        assertFalse(Files.exists(path));
    }

}