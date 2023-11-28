package com.masm.quickpoll.repository;

import com.masm.quickpoll.model.Poll;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long>,
        ListCrudRepository<Poll, Long> {

    //TODO: add unit test
    List<Poll> findByQuestionContaining(String question);



}
