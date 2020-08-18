package com.project.repository;

import com.project.model.AuthenticationCredentials;
import com.project.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationCredentialsRepository extends CrudRepository<AuthenticationCredentials,Integer> {

    @Query(
            value = "UPDATE auth_code\n" +
                    "SET latest_code = ?1 " +
                    "WHERE person_id = ?2",
            nativeQuery = true)
    AuthenticationCredentials updatePersonAuthCode(String authCode, Integer personId);


    @Query(
            value = "SELECT a.id FROM auth_code a WHERE a.person_id = ?1",
            nativeQuery = true)
    Long findByPersonId(Integer personId);

}
