package com.project.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_code")
public class AuthenticationCredentials {
        @Id
        @GeneratedValue( strategy= GenerationType.AUTO )
        @Column(name = "id")
        private Long id;

        @OneToOne
        @JoinColumn(name = "person_id")
        private Person person;

        @Column(name = "latest_code")
        private String latest_code;

    public AuthenticationCredentials() {
    }

    public AuthenticationCredentials(Long id, Person person, String latest_code) {
        this.id = id;
        this.person = person;
        this.latest_code = latest_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLatest_code() {
        return latest_code;
    }

    public void setLatest_code(String latest_code) {
        this.latest_code = latest_code;
    }
}
