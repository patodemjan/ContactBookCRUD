package com.crudApp.myCrudAppBooks.repository;


import com.crudApp.myCrudAppBooks.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
