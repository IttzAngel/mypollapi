package com.pollingapi.angelpollapi.controller;

import com.pollingapi.angelpollapi.exception.ResourceNotFoundException;
import com.pollingapi.angelpollapi.domain.Poll;
import com.pollingapi.angelpollapi.repositories.PollRepository;
import com.pollingapi.angelpollapi.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value="/polls", method=RequestMethod.GET) //get retrieves data
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        return pollService.getAllPolls();
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET) // get retrieves data, in this case it retrieves a poll by the pollid
    public Optional<Poll> getPoll(@PathVariable Long pollId){
        return pollService.getPoll(pollId);
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST) //post submits data
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll){
        return pollService.createPoll(poll);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT) // put is used to update and puts the new data in
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        return pollService.updatePoll(poll, pollId);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        return pollService.deletePoll(poll, pollId);
    }
}
