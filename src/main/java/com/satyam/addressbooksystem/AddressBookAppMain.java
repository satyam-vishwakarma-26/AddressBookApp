package com.satyam.addressbooksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		
		AddressBook addressBook = new AddressBook();

        addressBook.addContact();
        
		//SpringApplication.run(AddressBookAppMain.class, args);
	}

}
