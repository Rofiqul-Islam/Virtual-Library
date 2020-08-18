package com.project.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_code")
public class AuthenticationCredentials {
        @Id
        @Column(name = "id")
        private Integer id;

        @Column(name = "person_id")
        private Integer person_id;

        @Column(name = "latest_code")
        private String latest_code;

    public AuthenticationCredentials() {
    }

    public AuthenticationCredentials(Integer id, Integer person_id, String latest_code) {
        this.id = id;
        this.person_id = person_id;
        this.latest_code = latest_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getLatest_code() {
        return latest_code;
    }

    public void setLatest_code(String latest_code) {
        this.latest_code = latest_code;
    }
}
