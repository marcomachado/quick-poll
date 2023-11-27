package com.masm.quickpoll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long id;

    @Column(name="USERNAME")
    @NotEmpty
    private String username;

    @Column(name="PASSWORD")
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(name="FIRST_NAME")
    @NotEmpty
    private String firstName;

    @Column(name="LAST_NAME")
    @NotEmpty
    private String lastName;

    @Basic
    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    private boolean admin;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
