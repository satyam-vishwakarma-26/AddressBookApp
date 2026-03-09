package com.satyam.addressbooksystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    ArrayList<ContactPerson> contactList = new ArrayList<>();

    AddressBookDBService dbService = new AddressBookDBService();


    // FIXED: Add Contact → Memory + DB
    public void addContact(ContactPerson contact) {

        if(contactList.contains(contact)) {

            System.out.println("Duplicate contact found. Cannot add.");
            return;
        }

        contactList.add(contact);

        // Insert into DB
        dbService.insertContactToDB(contact);

        System.out.println("Contact added successfully");
    }


    public void displayContacts() {

        if(contactList.isEmpty()) {

            System.out.println("No contacts available");
            return;
        }

        for(ContactPerson contact : contactList) {

            contact.displayContact();
            System.out.println("---------------------");
        }
    }


    // Edit Contact → Memory + DB
    public void editContact(String firstName, String lastName, Scanner sc) {

        for(ContactPerson contact : contactList) {

            if(contact.getFirstName().equalsIgnoreCase(firstName)
                    && contact.getLastName().equalsIgnoreCase(lastName)) {

                System.out.println("Enter new Address:");
                contact.setAddress(sc.nextLine());

                System.out.println("Enter new City:");
                contact.setCity(sc.nextLine());

                System.out.println("Enter new State:");
                contact.setState(sc.nextLine());

                System.out.println("Enter new Zip:");
                contact.setZip(sc.nextLine());

                System.out.println("Enter new Phone:");
                contact.setPhoneNumber(sc.nextLine());

                System.out.println("Enter new Email:");
                contact.setEmail(sc.nextLine());

                // Sync DB
                dbService.updateContactInDB(contact);

                System.out.println("Contact updated successfully");
                return;
            }
        }

        System.out.println("Contact not found");
    }


    public void deleteContact(String firstName, String lastName) {

        boolean removed = contactList.removeIf(contact ->
                contact.getFirstName().equalsIgnoreCase(firstName)
                        && contact.getLastName().equalsIgnoreCase(lastName));

        if(removed)
            System.out.println("Contact deleted successfully");
        else
            System.out.println("Contact not found");
    }


    public void sortContactsByName() {

        if(contactList.isEmpty()) {

            System.out.println("No contacts available");
            return;
        }

        contactList.stream()
                .sorted((c1, c2) ->
                        c1.getFirstName().compareToIgnoreCase(c2.getFirstName()))
                .forEach(System.out::println);
    }


    public List<ContactPerson> getContacts() {

        return contactList;
    }
}