package com.project.controller;

import com.project.model.Person;
import com.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/all")
    public Iterable<Person> findAll(){
        System.out.println("reached here");
        return personRepository.findAll();
    }


}
