package com.project.repository;

import com.project.model.Person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findByEmailAndPassword(String email, String password);

    @Query(
            value = "SELECT * FROM person p WHERE p.email = ?1 AND p.password = ?2",
            nativeQuery = true)
    Person findWithLoginCredential(String email, String password);
}
