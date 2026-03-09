package com.satyam.addressbooksystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private static final String URL = "jdbc:mysql://localhost:3306/addressbook";
    private static final String USER = "root";
    private static final String PASSWORD = "9981";

    // UC16 Retrieve Contacts
    public List<ContactPerson> readContactsFromDB() {

        List<ContactPerson> contactList = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "SELECT * FROM contact";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                ContactPerson contact = new ContactPerson(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phone"),
                        rs.getString("email")
                );

                contactList.add(contact);
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database Error: " + e.getMessage());
        }

        return contactList;
    }


    // UC17 INSERT CONTACT (SYNC MEMORY + DB)
    public void insertContactToDB(ContactPerson contact) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "INSERT INTO contact(first_name,last_name,address,city,state,zip,phone,email) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getAddress());
            ps.setString(4, contact.getCity());
            ps.setString(5, contact.getState());
            ps.setString(6, contact.getZip());
            ps.setString(7, contact.getPhoneNumber());
            ps.setString(8, contact.getEmail());

            ps.executeUpdate();

            connection.close();

            System.out.println("Contact inserted into Database");

        } catch(Exception e) {

            System.out.println("Insert Error: " + e.getMessage());
        }
    }


    // UC17 UPDATE CONTACT
    public void updateContactInDB(ContactPerson contact) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "UPDATE contact SET address=?,city=?,state=?,zip=?,phone=?,email=? WHERE first_name=? AND last_name=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, contact.getAddress());
            ps.setString(2, contact.getCity());
            ps.setString(3, contact.getState());
            ps.setString(4, contact.getZip());
            ps.setString(5, contact.getPhoneNumber());
            ps.setString(6, contact.getEmail());
            ps.setString(7, contact.getFirstName());
            ps.setString(8, contact.getLastName());

            ps.executeUpdate();

            connection.close();

            System.out.println("Contact updated in Database");

        } catch(Exception e) {

            System.out.println("Update Error: " + e.getMessage());
        }
    }


    public void printContacts(List<ContactPerson> contacts) {

        if(contacts.isEmpty()) {

            System.out.println("No contacts found in database");
            return;
        }

        for(ContactPerson contact : contacts) {

            System.out.println(
                    contact.getFirstName() + " " +
                    contact.getLastName() + " | " +
                    contact.getCity() + " | " +
                    contact.getState() + " | " +
                    contact.getPhoneNumber()
            );
        }
    }
}