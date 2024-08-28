package org.lab;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    //TODO: Create a framework that processes a collection of objects
    //(e.g. , Person entities with attributes like name, age, email)
    // this framework should provide functionnalities to filter,sort, and perfom
    // various agregate operations on the collection
    // similar to what you might find in a database or a data processing library.
    // Use Geberiques to allw your framework to work with anx type of objects

    public static void main(String[] args) {

        Repository<Person> personRepository = buildPersonRepository();

     //Find person under age 25
        //since Cryterium is a lambda expression,see Criterium.java
        List<Person> personsUnder25 = personRepository.findByCriterium(person -> person.getAge() < 25);
        //personsUnder25.stream().forEach(System.out::println);

     //Sort persons
           //1 alternate way
        List<Person> sortedPersons = personRepository.sorted((p1, p2) -> p1.getAge() - p2.getAge());
        //sortedPersons.stream().forEach(System.out::println);
            //2 alternate way
        List<Person> sortedPersons2 = personRepository.sorted(Comparator.comparing(Person::getName));
       // sortedPersons2.stream().forEach(System.out::println);

    //Get only Emails
        List<String> emails = personRepository.findAll().stream().map(Person::getEmail).collect(Collectors.toList());
        emails.stream().forEach(System.out::println);
          //mapToInt => returns  only stream of integers
        double averageAge = personRepository.findAll().stream().mapToInt(Person::getAge).average().orElse(0);
        System.out.println("the average : " + averageAge);
          //Sum of all ages
        int sumAge = personRepository.findAll().stream().mapToInt(Person::getAge).sum();
        System.out.println("the sum : " + sumAge);

        //Get first person with name name starting with 'z'

        Optional<Person> personOptional = personRepository.findFirstByCriterium(person -> person.getName().startsWith("z"));
       personOptional.ifPresent(System.out::println);

       // personRepository.findAll().forEach(System.out::println);

    }

    static  Repository<Person> buildPersonRepository() {
        Repository<Person> personRepository = new Repository<>();
        personRepository.add(new Person("john",30,"doe@mail.de"));
        personRepository.add(new Person("rico",32,"koe@mail.de"));
        personRepository.add(new Person("val",28,"val@mail.de"));
        personRepository.add(new Person("gota",25,"gto@mail.de"));
        personRepository.add(new Person("louis",30,"luis@mail.de"));
        personRepository.add(new Person("namo",20,"namo@mail.de"));
        personRepository.add(new Person("huho",30,"huho@mail.de"));
        personRepository.add(new Person("zuzo",30,"zuzo@mail.de"));
        personRepository.add(new Person("qolo",50,"qolo@mail.de"));
        personRepository.add(new Person("xop",32,"xop@mail.de"));

        return personRepository;
    }
}