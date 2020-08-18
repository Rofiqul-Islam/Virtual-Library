package com.project.repository;

import com.project.model.AuthenticationCredentials;
import com.project.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationCredentialsRepository extends CrudRepository<AuthenticationCredentials,Integer> {
}
