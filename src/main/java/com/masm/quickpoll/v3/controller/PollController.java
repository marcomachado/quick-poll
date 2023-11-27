package com.masm.quickpoll.v3.controller;

import com.masm.quickpoll.exception.ResourceNotFoundException;
import com.masm.quickpoll.model.Poll;
import com.masm.quickpoll.repository.PollRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController("pollControllerV3")
@RequestMapping ("/v3")
public class PollController {

    final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll newPoll) {
        newPoll = pollRepository.save(newPoll);

        // Set the location header for the newly created resource
        //location with URI to new poll

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPoll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/createpolls")
    public ResponseEntity<?> createPolls(@RequestBody List<Poll> listPolls) {

        for (Poll n: listPolls) {
            n = pollRepository.save(n);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        return new ResponseEntity<>(verifyPoll(pollId), HttpStatus.OK);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable
    Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        Poll newPoll = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls")
    public ResponseEntity<?> deletePoll() {
        pollRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Poll verifyPoll(Long pollId) {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (!poll.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " +
                    pollId + " not found");
        }
        return poll.get();
    }
}
