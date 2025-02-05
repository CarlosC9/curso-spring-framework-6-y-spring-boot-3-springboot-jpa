package com.carlos.curso.springboot.jpa.springbootjpa;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import com.carlos.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

    @Override
    public void run(String... args) throws Exception {
        List<Person> persons = (List<Person>) personRepository.findAll();
        persons.forEach(System.out::println);

    }
}
