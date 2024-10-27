package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// Make methods static to not have to crate the object
public class CustomerFileReader {
    private static final String CSV_CUSTOMERS_INFO = "data/Customers.csv";

    // Save customer info
    public static void saveCustomer(Customer customer)  {
        List<Customer> existingCustomers = retrieveAllCustomers();

        for (Customer existingCustomer : existingCustomers){
            if (existingCustomer != null) {
                if (existingCustomer.getId() == customer.getId()) {
                    System.out.println("Customer " + customer.getFirstName() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try(PrintWriter writer = new PrintWriter(new FileWriter(CSV_CUSTOMERS_INFO, true))) {  // Append mode
            writer.println(customerToCSV(customer));
        }catch (IOException e){}

    }

    // Converts Customer object to an entry in Customers.csv
    private static String customerToCSV(Customer customer){
        return customer.getId() +
                ";" + customer.getFirstName() +
                ";" + customer.getLastName() +
                ";" + customer.getEmail() +
                ";" + customer.getAddress();
    }

    // Retrieve a specific customer by ID
    public static Customer retrieveCustomer(int id)  {
        List<Customer> customers = retrieveAllCustomers();
        for (Customer customer : customers) {
            if(customer == null) continue;
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }


    // Return list of customers
    public static List<Customer> retrieveAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(CSV_CUSTOMERS_INFO);
        int maxID = 0;

        if (!file.exists()) {
            return customers;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(csvToCustomer(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Removing null value
        customers.remove(0);

        /*---------------------------*/
        // Setting next available ID
        // Need to create a separate method in the future to initialize the next available ID on startup
        maxID = customers.getLast().getId();
        Customer.setNextID(maxID);
        /*---------------------------*/
        return customers;
    }

    // Convert an entry in Customers.csv to a Customer object
    public static Customer csvToCustomer(String line){
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