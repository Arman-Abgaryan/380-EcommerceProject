package com.example.comp380project;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFileReaderTest {

    @Test
    void saveCustomer() {
        Customer testCustomer = new Customer(6, "John", "Test", "testjohn@example.com", "1234 Test Ave", "JohnTest", "testpassword");

        CustomerFileReader.saveCustomer(testCustomer);

        Customer retrievedTestCustomer = CustomerFileReader.retrieveCustomer(testCustomer.getId());
        assertNotNull(retrievedTestCustomer);
        assertEquals("John", retrievedTestCustomer.getFirstName());
        assertEquals("Test", retrievedTestCustomer.getLastName());
        assertEquals("testjohn@example.com", retrievedTestCustomer.getEmail());
        assertEquals("1234 Test Ave", retrievedTestCustomer.getAddress());
        assertEquals("JohnTest", retrievedTestCustomer.getUsername());
        assertEquals("testpassword", retrievedTestCustomer.getPassword());
    }

    @Test
    void retrieveCustomer() {
        Customer testCustomer = new Customer(5, "Jake", "Smith", "jakesmith@example.com", "111 Smith St", "JakeSmith", "jakesmith001");
        CustomerFileReader.saveCustomer(testCustomer);

        Customer retrievedTestCustomer = CustomerFileReader.retrieveCustomer(testCustomer.getId());

        assertNotNull(retrievedTestCustomer);
        assertEquals("Jake", retrievedTestCustomer.getFirstName());
        assertEquals("Smith", retrievedTestCustomer.getLastName());
        assertEquals("jakesmith@example.com", retrievedTestCustomer.getEmail());
        assertEquals("111 Smith St", retrievedTestCustomer.getAddress());
        assertEquals("JakeSmith", retrievedTestCustomer.getUsername());
        assertEquals("jakesmith001", retrievedTestCustomer.getPassword());

    }

    @Test
    void testRetrieveCustomerByUsername() {
        Customer retrievedTestCustomer = CustomerFileReader.retrieveCustomer(2);

        assertNotNull(retrievedTestCustomer);
        assertEquals("Arman", retrievedTestCustomer.getFirstName());
    }

    @Test
    void retrieveAllCustomers() {
        List<Customer> customers = CustomerFileReader.retrieveAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() >= 4);
        assertTrue(customers.stream().anyMatch(customer -> customer.getUsername().equals("Arman_A")));
        assertTrue(customers.stream().anyMatch(customer -> customer.getUsername().equals("aulloa")));
    }

    @Test
    void csvToCustomer() {
        String csvLineTest = "7;Test;Testington;test.email@example.com;333 Oak St;testUsername;testPassword";

        Customer customerTest = CustomerFileReader.csvToCustomer(csvLineTest);

        assertNotNull(customerTest);
        assertEquals(7, customerTest.getId());
        assertEquals("Test", customerTest.getFirstName());
        assertEquals("Testington", customerTest.getLastName());
        assertEquals("test.email@example.com", customerTest.getEmail());
        assertEquals("333 Oak St", customerTest.getAddress());
        assertEquals("testUsername", customerTest.getUsername());
        assertEquals("testPassword", customerTest.getPassword());
    }
}