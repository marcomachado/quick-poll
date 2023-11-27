package com.masm.quickpoll.v2.controller;

import com.masm.quickpoll.model.Vote;
import com.masm.quickpoll.repository.VoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController("voteControllerV2")
@RequestMapping ("/v2")
public class VoteController {

    final VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @PostMapping("polls/{pollId}/votes")
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}/votes")
    public ResponseEntity<?> getAllVotes(@PathVariable Long pollId) {
        Iterable<Vote> votes = voteRepository.findByPoll(pollId);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
}
