package com.carlos.curso.springboot.jpa.springbootjpa.repositories;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    

}
