package com.carlos.curso.springboot.jpa.springbootjpa;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import com.carlos.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public void update() {

    Optional<Person> optionalPerson = this.personRepository.findById(7L);

    if (optionalPerson.isPresent()) {
      Person person = optionalPerson.orElseThrow();
      Person personDb = personRepository.save(person);
      System.out.println(personDb);
    }

//    optionalPerson.ifPresent(person -> {
//      person.setProgrammingLanguage("Javascript");
//      Person personDb = personRepository.save(person);
//      System.out.println(personDb);
//    });

  }

  @Transactional
  public void create() {
    Person person = new Person(null, "Calo", "Calaballo", "Javascript");

    this.personRepository.findById(person.getId()).ifPresent(System.out::println);
  }

  @Transactional
  public void delete() {

    this.personRepository.deleteById(7L);

    List<Person> persons = (List<Person>) this.personRepository.findAll();
    persons.forEach(System.out::println);
  }

  @Transactional
  public void delete2() {

    Optional<Person> personOptional = this.personRepository.findById(8L);


    if (personOptional.isPresent()) {
      this.personRepository.delete(personOptional.orElseThrow());
    }

    List<Person> persons = (List<Person>) this.personRepository.findAll();
    persons.forEach(System.out::println);
  }

  @Transactional(readOnly = true)
  public void personalizedQueries() {

    System.out.println(personRepository.getNameById(2L).orElseThrow());
    System.out.println(personRepository.getFullNameById(2L).orElseThrow());
  }


  @Override
  public void run(String... args) throws Exception {
//    this.list();
//    this.findOne();
//    this.create();
//    this.update();
//    this.delete();
//    this.delete2();
    this.personalizedQueries();
  }
}
