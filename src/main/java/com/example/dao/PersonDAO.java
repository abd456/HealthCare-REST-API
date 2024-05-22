///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */



package com.example.dao;

import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abdhu
 */
public class PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAO.class);

    private static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(11, "John Doe", "johndoe@example.com", "123 Main Street"));
        people.add(new Person(12, "Alice Smith", "alicesmith@example.com", "456 Elm Street"));
        people.add(new Person(13, "Bob Johnson", "bjohnson@example.com", "789 Oak Street"));
    }

    public List<Person> getAllPeople() {
        LOGGER.info("Fetching all people");
        return people;
    }

    public Person getPersonById(int id) {
        LOGGER.info("Fetching person by ID: {}", id);
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        LOGGER.info("Adding person: {}", person);
        people.add(person);
    }

    public void updatePerson(Person updatedPerson) {
        LOGGER.info("Updating person with ID: {}", updatedPerson.getId());
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if (person.getId() == updatedPerson.getId()) {
                people.set(i, updatedPerson);
                LOGGER.info("Person updated: {}", updatedPerson);
                return;
            }
        }
    }

    public void deletePerson(int id) {
        LOGGER.info("Deleting person with ID: {}", id);
        people.removeIf(person -> person.getId() == id);
    }
}
