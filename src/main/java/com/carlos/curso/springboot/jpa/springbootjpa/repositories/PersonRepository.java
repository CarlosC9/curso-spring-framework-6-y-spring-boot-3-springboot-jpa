package com.carlos.curso.springboot.jpa.springbootjpa.repositories;

import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

  List<Person> findByProgrammingLanguage(String programmingLanguage);

  List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

  @Query("select p from Person p where p.programmingLanguage=:programmingLanguage and p.name=:name")
  List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);
}
