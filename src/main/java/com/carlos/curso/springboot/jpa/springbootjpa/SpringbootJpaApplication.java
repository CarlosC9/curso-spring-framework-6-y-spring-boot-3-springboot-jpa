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

    Optional<Person> optionalPerson = this.personRepository.findById(10L);

    if (optionalPerson.isPresent()) {
      Person person = optionalPerson.orElseThrow();
      person.setProgrammingLanguage("Python");
      Person personDb = this.personRepository.save(person);
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

    Person newPerson = this.personRepository.save(person);

    this.personRepository.findById(newPerson.getId()).ifPresent(System.out::println);
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

    System.out.println("\ngetNameById");
    System.out.println(this.personRepository.getNameById(2L).orElseThrow());

    System.out.println("\ngetFullNameById");
    System.out.println(this.personRepository.getFullNameById(2L).orElseThrow());

    System.out.println("\nobtenerPersonDataById");
    System.out.println(Arrays.toString(this.personRepository.obtenerPersonDataById(2L)));

    System.out.println("\nobtenerPersonDataList");
    this.personRepository.obtenerPersonDataList().forEach(
      p -> System.out.println(Arrays.toString(p))
    );
  }

  @Transactional(readOnly = true)
  public void personalizedQueries2() {

    System.out.println("\nfindAllMixPersonDataList");
    this.personRepository.findAllMixPersonDataList().forEach(
      reg -> System.out.println(Arrays.toString(reg))
    );

    System.out.println("\nfindAllClassPerson");
    this.personRepository.findAllPersonalizedObjectPerson().forEach(System.out::println);

    System.out.println("\nfindAllPersonDto");
    this.personRepository.findAllPersonDto().forEach(System.out::println);
  }

  @Transactional(readOnly = true)
  public void personalizedQueriesDistinct() {

    System.out.println("\nfindAllName");
    this.personRepository.findAllName().forEach(System.out::println);

    System.out.println("\nfindAllNamesDistinct");
    this.personRepository.findAllNamesDistinct().forEach(System.out::println);

    System.out.println("\nfindAllProgrammingLanguageDistinct");
    this.personRepository.findAllProgrammingLanguageDistinct().forEach(System.out::println);

    System.out.println("\nfindAllProgrammingLanguageDistinctCount");
    System.out.println(this.personRepository.findAllProgrammingLanguageDistinctCount());
  }

  @Transactional(readOnly = true)
  public void personalizedQueriesContactUpperAndLowerCase() {

    System.out.println("\nfindAllFullNameConcat");
    this.personRepository.findAllFullNameConcat().forEach(System.out::println);

    System.out.println("\nfindAllFullNameConcatUpper");
    this.personRepository.findAllFullNameConcatUpper().forEach(System.out::println);

    System.out.println("\nfindAllFullNameConcatLower");
    this.personRepository.findAllFullNameConcatLower().forEach(System.out::println);

    System.out.println("\nfindAllPersonDataListCase");
    this.personRepository.findAllPersonDataListCase()
      .forEach((register) -> System.out.println(Arrays.toString(register)));
  }

  @Transactional(readOnly = true)
  public void personalizedQueriesBetween() {

    System.out.println("\nfindAllBetweenId");
    this.personRepository.findAllBetweenId(2L, 5L).forEach(System.out::println);

    System.out.println("\nfindAllBetweenName");
    this.personRepository.findAllBetweenName("J", "Q").forEach(System.out::println);

    System.out.println("\nfindByIdBetweenOrderByNameAsc");
    this.personRepository.findByIdBetweenOrderByNameAsc(2L, 5L).forEach(System.out::println);

    System.out.println("\nfindByNameBetweenOrderByNameDescLastnameDesc");
    this.personRepository.findByNameBetweenOrderByNameDescLastnameDesc("J", "Q").forEach(System.out::println);

    System.out.println("\ngetAll");
    this.personRepository.getAllOrdered().forEach(System.out::println);

    System.out.println("\nfindAllByOrderByNameAscLastnameAsc");
    this.personRepository.findAllByOrderByNameAscLastnameAsc().forEach(System.out::println);
  }

  @Transactional(readOnly = true)
  public void queriesFunctionAggregation() {

    System.out.println("\ngetTotalPerson");
    System.out.println(this.personRepository.getTotalPerson());

    System.out.println("\ngetMinId");
    System.out.println(this.personRepository.getMinId());

    System.out.println("\ngetMaxId");
    System.out.println(this.personRepository.getMaxId());

    System.out.println("\ngetPersonNameLength");
    this.personRepository.getPersonNameLength()
      .forEach((register) -> System.out.println(Arrays.toString(register)));

    System.out.println("\ngetMinLengthName");
    System.out.println(this.personRepository.getMinLengthName());

    System.out.println("\ngetMaxLengthName");
    System.out.println(this.personRepository.getMaxLengthName());

    System.out.println("\ngetResumeAggregationFunction");
    for (Object col : (Object[]) this.personRepository.getResumeAggregationFunction()) {
      System.out.println(col);
    }
  }

  @Transactional(readOnly = true)
  public void subQueries() {
    System.out.println("\ngetShorterName");
    this.personRepository.getShorterName()
      .forEach((register) -> System.out.println(Arrays.toString(register)));

    System.out.println("\ngetLastRegistration");
    this.personRepository.getLastRegistration().ifPresent(System.out::println);
  }

  @Transactional(readOnly = true)
  public void whereIn() {
    System.out.println("\ngetPersonByIds");
    this.personRepository.getPersonByIds(Arrays.asList(1L, 2L, 5L)).forEach(System.out::println);
  }


  @Override
  public void run(String... args) throws Exception {
//    this.list();
//    this.findOne();
//    this.create();
//    this.update();
//    this.delete();
//    this.delete2();
//    this.personalizedQueries();
//    this.personalizedQueries2();
//    this.create();
//    this.personalizedQueriesDistinct();
//    this.personalizedQueriesContactUpperAndLowerCase();
//    this.personalizedQueriesBetween();
//    this.queriesFunctionAggregation();
//    this.subQueries();
//    this.whereIn();
  }
}
