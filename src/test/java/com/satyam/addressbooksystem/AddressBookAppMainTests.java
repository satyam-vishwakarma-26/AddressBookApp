package com.satyam.addressbooksystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookAppMainTests {

    private AddressBook addressBook;
    private AddressBookSystem system;
    private ContactPerson contact1;
    private ContactPerson contact2;

    @BeforeEach
    void setup() {

        addressBook = new AddressBook();
        system = new AddressBookSystem();

        contact1 = new ContactPerson(
                "Satyam",
                "Vishwakarma",
                "Ayodhya Nagar",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "satyam@gmail.com"
        );

        contact2 = new ContactPerson(
                "Rahul",
                "Sharma",
                "Vijay Nagar",
                "Indore",
                "MP",
                "452001",
                "9999999999",
                "rahul@gmail.com"
        );
    }

    @Test
    void givenContact_WhenAdded_ShouldBeInContactList() {

        addressBook.addContact(contact1);

        List<ContactPerson> contacts = addressBook.getContacts();

        assertEquals(1, contacts.size());
        assertEquals("Satyam", contacts.get(0).getFirstName());
    }

    @Test
    void givenDuplicateContact_WhenAdded_ShouldNotIncreaseListSize() {

        addressBook.addContact(contact1);
        addressBook.addContact(contact1);

        assertEquals(1, addressBook.getContacts().size());
    }

    @Test
    void givenContact_WhenDeleted_ShouldRemoveFromList() {

        addressBook.addContact(contact1);

        addressBook.deleteContact("Satyam", "Vishwakarma");

        assertEquals(0, addressBook.getContacts().size());
    }

    @Test
    void givenContact_WhenEdited_ShouldUpdateContact() {

        addressBook.addContact(contact1);

        String input =
                "New Address\n" +
                "Delhi\n" +
                "Delhi\n" +
                "110001\n" +
                "8888888888\n" +
                "new@gmail.com\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        addressBook.editContact("Satyam", "Vishwakarma", scanner);

        ContactPerson updated = addressBook.getContacts().get(0);

        assertEquals("Delhi", updated.getCity());
        assertEquals("8888888888", updated.getPhoneNumber());
    }

    @Test
    void givenContacts_WhenAdded_ShouldReturnCorrectCount() {

        addressBook.addContact(contact1);
        addressBook.addContact(contact2);

        assertEquals(2, addressBook.getContacts().size());
    }

    @Test
    void givenAddressBookName_WhenAdded_ShouldExistInSystem() {

        system.addAddressBook("Home");

        AddressBook book = system.getAddressBook("Home");

        assertNotNull(book);
    }

    @Test
    void givenPerson_WhenAddedToDictionary_ShouldBeStoredInCityDictionary() {

        system.addPersonToDictionary(contact1);

        assertTrue(system.cityDictionary.containsKey("Bhopal"));
    }

    @Test
    void givenPersonsInCity_WhenCounted_ShouldReturnCorrectCount() {

        system.addPersonToDictionary(contact1);
        system.addPersonToDictionary(contact2);

        long count = system.cityDictionary.get("Bhopal").size();

        assertEquals(1, count);
    }

    @Test
    void givenMultipleContacts_WhenSorted_ShouldNotThrowException() {

        addressBook.addContact(contact2);
        addressBook.addContact(contact1);

        assertDoesNotThrow(() -> addressBook.sortContactsByName());
    }
    
    @Test
    void givenTwoContactsWithSameName_ShouldBeEqual() {

        ContactPerson person1 = new ContactPerson(
                "Aman","Sharma","A","Bhopal","MP","111","999","a@gmail.com"
        );

        ContactPerson person2 = new ContactPerson(
                "Aman","Sharma","B","Delhi","DL","222","888","b@gmail.com"
        );

        assertEquals(person1, person2);
    }
    @Test
    void givenContact_WhenCreated_ShouldStoreCorrectInformation() {

        ContactPerson person = new ContactPerson(
                "Ravi",
                "Kumar",
                "Indrapuri",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "ravi@gmail.com"
        );

        assertEquals("Ravi", person.getFirstName());
        assertEquals("Kumar", person.getLastName());
        assertEquals("Bhopal", person.getCity());
    }
}