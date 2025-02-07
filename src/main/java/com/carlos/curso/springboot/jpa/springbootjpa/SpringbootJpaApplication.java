package com.carlos.curso.springboot.jpa.springbootjpa;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import com.carlos.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

  private PersonRepository personRepository;

  @Autowired
  public void setPersonRepository(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaApplication.class, args);
  }

  private void list() {
    //    List<Person> persons = (List<Person>) this.personRepository.findAll();
//    List<Person> persons = this.personRepository.findByProgrammingLanguage("Javascript");
//    List<Person> persons = this.personRepository.buscarByProgrammingLanguage("Python", "Pepe");
    List<Person> persons = this.personRepository
      .findByProgrammingLanguageAndName("Java", "Andres");

    persons.forEach(System.out::println);

    List<Object[]> personsValues = this.personRepository.obtenerPersonData();
    personsValues.forEach(person -> {
      System.out.println(Arrays.toString(person));
    });
  }

  private void findOne() {
    Person person = null;
    Optional<Person> optionalPerson = this.personRepository.findByNameContainingIgnoreCase("ri");
    if (optionalPerson.isPresent()) {
      person = optionalPerson.orElseThrow();
    }
    System.out.println(person);
  }

  public void create() {
    Person person = new Person(null, "Lalo", "Thor", "Python");

    Person newPerson = this.personRepository.save(person);

    System.out.println(newPerson);
  }

  @Override
  public void run(String... args) throws Exception {
//    this.list();
//    this.findOne();
    this.create();
  }
}
