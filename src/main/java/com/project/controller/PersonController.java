package com.project.controller;

import com.project.model.Person;
import com.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import javax.swing.text.html.Option;

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

    @PostMapping("/user/register")
    public ResponseEntity<Person> createUser(@RequestBody Person person){
        System.out.println(person.getName());
        try {
//            Person p = new Person();//
//            p.setEmail(person.getEmail());
//            p.setName(person.getName());
//            p.setPassword(person.getPassword());
//            p.setRole(person.getRole());
//            p.setVerified(person.getVerified());
//            personRepository.save(p);
            Person p = personRepository.save(new Person((long)0,person.getName(),person.getPassword(),person.getEmail(),person.getRole(),person.getVerified()));
           return new ResponseEntity<>(p, HttpStatus.CREATED);


        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Person> getPersonByEmailAndPassword(@RequestBody Person person){
        Person p = personRepository.findByEmailAndPassword(person.getEmail(),person.getPassword());
        System.out.println(p.getEmail());
        if(p !=null){
            return new ResponseEntity<>(p,HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("user/update/{id}")
    public ResponseEntity<Person> updateUser( @PathVariable("id") Long id, @RequestBody Person person){
        Optional<Person> personData = personRepository.findById(id);

        if ( personData.isPresent()){
            Person p = personData.get();
            p.setEmail(person.getEmail());
            p.setName(person.getName());
            p.setPassword(person.getPassword());
            p.setVerified(person.getVerified());
            p.setRole(person.getRole());
            return new ResponseEntity<>(personRepository.save(p),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
