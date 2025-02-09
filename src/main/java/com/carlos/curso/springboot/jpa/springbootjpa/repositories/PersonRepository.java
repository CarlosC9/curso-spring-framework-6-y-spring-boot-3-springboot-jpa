package com.carlos.curso.springboot.jpa.springbootjpa.repositories;

import com.carlos.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.carlos.curso.springboot.jpa.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p) from Person p ")
  public Object getResumeAggregationFunction();

  @Query("select max(length(p.name)) from Person p")
  public Integer getMaxLengthName();

  @Query("select min(length(p.name)) from Person p")
  public Integer getMinLengthName();

  @Query("select p.name, length(p.name) from Person p")
  public List<Object[]> getPersonNameLength();

  @Query("select count(p) from Person p")
  Long getTotalPerson();

  @Query("select min(p.id) from Person p")
  Long getMinId();

  @Query("select max(p.id) from Person p")
  Long getMaxId();

  List<Person> findAllByOrderByNameAscLastnameAsc();

  @Query("select p from Person p order by p.name, p.lastname desc ")
  List<Person> getAllOrdered();

  List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

  List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String c1, String c2);

  @Query("select p from Person p where p.name between :c1 and :c2 order by p.name asc , p.lastname desc")
  List<Person> findAllBetweenName(String c1, String c2);

  @Query("select p from Person p where p.id between :id1 and :id2 order by p.name desc")
  List<Person> findAllBetweenId(Long id1, Long id2);

  @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
  List<Object[]> findAllPersonDataListCase();

  @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
  List<String> findAllFullNameConcatLower();

  @Query("select upper(p.name || ' ' || p.lastname) from Person p")
  List<String> findAllFullNameConcatUpper();

  //  @Query("select concat(p.name, ' ', p.lastname) from Person p")
  @Query("select p.name || ' ' || p.lastname from Person p")
  List<String> findAllFullNameConcat();

  @Query("select count(distinct(p.programmingLanguage)) from Person p")
  Long findAllProgrammingLanguageDistinctCount();

  @Query("select distinct(p.programmingLanguage) from Person p")
  List<String> findAllProgrammingLanguageDistinct();

  @Query("select p.name from Person p")
  List<String> findAllName();

  @Query("select distinct(p.name) from Person p")
  List<String> findAllNamesDistinct();

  @Query("select new com.carlos.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
  List<PersonDto> findAllPersonDto();

  @Query("select new Person(p.name, p.lastname) from Person p")
  List<Person> findAllPersonalizedObjectPerson();

  @Query("select p.name from Person p where p.id=:id")
  Optional<String> getNameById(Long id);

  @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=:id")
  Optional<String> getFullNameById(Long id);

  @Query("select p from Person p where p.id=:id")
  Optional<Person> findOne(Long id);

  @Query("select p from Person p where p.name=:name")
  Optional<Person> findOneName(String name);

  @Query("select p from Person p where p.name like %:name%")
  Optional<Person> findOneLikeName(String name);

  Optional<Person> findByNameContainingIgnoreCase(String name);

  List<Person> findByProgrammingLanguage(String programmingLanguage);

  List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

  @Query("select p from Person p where p.programmingLanguage=:programmingLanguage and p.name=:name")
  List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

  @Query("select p.name, p.programmingLanguage from Person p")
  List<Object[]> obtenerPersonData();

  @Query("select p, p.programmingLanguage from Person p")
  List<Object[]> findAllMixPersonDataList();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
  List<Object[]> obtenerPersonDataList();

  @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=:id")
  Object[] obtenerPersonDataById(Long id);


}
