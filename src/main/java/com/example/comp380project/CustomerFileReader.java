package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFileReader {
    private static final String CSV_CUSTOMERS_INFO = "data/Customers.csv";

    // Save customer info
    public void saveCustomer(Customer customer) throws IOException, ClassNotFoundException {
        List<Customer> existingCustomers = retrieveAllCustomers();

        for (Customer existingCustomer : existingCustomers){
            if (existingCustomer != null) {
                if (existingCustomer.getId() == customer.getId()) {
                    System.out.println("Customer " + customer.getFirstName() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_CUSTOMERS_INFO, true))) {  // Append mode
            writer.println(customerToCSV(customer));
        }
    }

    // Converts Customer object to an entry in Customers.csv
    private String customerToCSV(Customer customer){
        return customer.getId() +
                ";" + customer.getFirstName() +
                ";" + customer.getLastName() +
                ";" + customer.getEmail() +
                ";" + customer.getAddress();
    }

    // Retrieve a specific customer by ID
    public Customer retrieveCustomer(int id) throws IOException, ClassNotFoundException {
        List<Customer> customers = retrieveAllCustomers();
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }


    // Return list of customers
    public List<Customer> retrieveAllCustomers() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        File file = new File(CSV_CUSTOMERS_INFO);

        if (!file.exists()) {
            return customers;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(csvToCustomer(line));
            }
        }
        return customers;
    }

    // Convert an entry in Customers.csv to a Customer object
    public Customer csvToCustomer(String line){
    String[] data = line.split(";");
    if(data[0].equalsIgnoreCase("id")) return null;

    int id = Integer.parseInt(data[0]);
    String firstName = data[1];
    String lastName = data[2];
    String email = data[3];
    String address = data[4];

    return new Customer(id, firstName, lastName, email, address);
    }
}