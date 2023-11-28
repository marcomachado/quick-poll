package com.masm.quickpoll.repository;

import com.masm.quickpoll.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //TODO: add unit tests
/*
    List<User> findByFirstNameAndLastName(String firstName, String lastName);


    //TODO: how to concatenate two fields?
    @Value("#{target.firstname + ' ' + target.lastname}")
    Optional<User> getFullName();

    //List<User> findByFirstNameContainingAndLastNameContainingAllIgnoreCase(String firstname, String lastname);

    @Value("#{@user.getCompleteName(target)}")
    Optional<User> findByCompleteName(String name);

    List<User> findByFirstNameStartsWithIgnoreCase(String firstName);

     */

}