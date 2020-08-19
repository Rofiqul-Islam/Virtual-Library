package com.project.repository;

import com.project.model.AuthenticationCredentials;
import com.project.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationCredentialsRepository extends CrudRepository<AuthenticationCredentials,Long> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE auth_code\n" +
                    "SET latest_code = ?1 " +
                    "WHERE person_id = ?2",
            nativeQuery = true)
    void updatePersonAuthCode(String authCode, Long personId);


    @Query(
            value = "SELECT a.id FROM auth_code a WHERE a.person_id = ?1",
            nativeQuery = true)
    Long findByPersonId(Long personId);

}
