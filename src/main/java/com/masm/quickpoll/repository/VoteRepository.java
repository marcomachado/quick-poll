package com.masm.quickpoll.repository;

import com.masm.quickpoll.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;


public interface VoteRepository extends ListCrudRepository<Vote, Long> {

    //TODO: it is possible to refactor to use only property expression?
    @Query(value = "select v.* from Option o, Vote v " +
            "where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID",
            nativeQuery = true)
    Iterable<Vote> findByPoll(Long id);
}
