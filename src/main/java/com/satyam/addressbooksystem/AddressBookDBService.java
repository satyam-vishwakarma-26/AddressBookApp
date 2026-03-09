package com.satyam.addressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private static final String URL = "jdbc:mysql://localhost:3306/addressbook";
    private static final String USER = "root";
    private static final String PASSWORD = "9981";

   
     // UC16

    public List<ContactPerson> readContactsFromDB() {

        List<ContactPerson> contactList = new ArrayList<>();

        try {

            // Step 1: Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Create Connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Step 3: SQL Query
            String query = "SELECT * FROM contact";

            // Step 4: Create Statement
            Statement statement = connection.createStatement();

            // Step 5: Execute Query
            ResultSet resultSet = statement.executeQuery(query);

            // Step 6: Process Result
            while (resultSet.next()) {

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                ContactPerson contact = new ContactPerson(
                        firstName,
                        lastName,
                        address,
                        city,
                        state,
                        zip,
                        phone,
                        email
                );

                contactList.add(contact);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return contactList;
    }

    /**
     * Display contacts
     */
    public void printContacts(List<ContactPerson> contacts) {

        if (contacts.isEmpty()) {
            System.out.println("No contacts found in database.");
            return;
        }

        for (ContactPerson contact : contacts) {

            System.out.println(
                    contact.getFirstName() + " "
                    + contact.getLastName() + " | "
                    + contact.getCity() + " | "
                    + contact.getState() + " | "
                    + contact.getPhoneNumber()
            );
        }
    }
}