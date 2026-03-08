package com.satyam.addressbooksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		
		AddressBook addressBook = new AddressBook();

        ContactPerson person = addressBook.createContact();

        System.out.println("\nContact Created Successfully!");

        System.out.println(person.firstName + " " + person.lastName);
        System.out.println(person.address);
        System.out.println(person.city + ", " + person.state);
        System.out.println("Zip: " + person.zip);
        System.out.println("Phone: " + person.phoneNumber);
        System.out.println("Email: " + person.email);
        
		//SpringApplication.run(AddressBookAppMain.class, args);
	}

}
