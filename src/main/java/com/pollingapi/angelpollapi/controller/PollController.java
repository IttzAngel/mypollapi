package com.pollingapi.angelpollapi.controller;

import com.pollingapi.angelpollapi.exception.ResourceNotFoundException;
import com.pollingapi.angelpollapi.domain.Poll;
import com.pollingapi.angelpollapi.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @RequestMapping(value="/polls", method=RequestMethod.GET) //get retrieves data
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST) //post submits data
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders(); //response header is sent by server to client and provides additional information about response, for example content-type header would specify the type of content that the server is sending
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri(); //uri is to identify resources that can be accessed over a network, it requires a scheme(http://) and a path(/{id})
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, HttpStatus.CREATED); //this will replace the path variable with the variable passed in
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET) // get retrieves data, in this case it retrieves a poll by the pollid
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
        Optional<Poll> p = pollRepository.findById(pollId); //findById method is (Optional<T> findById(ID id);). I need an object of type Optional<Poll> and not just Poll.
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT) // put is used to update and puts the new data in
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        Poll p = pollRepository.save(poll); // .save needs an entity parameter, which Poll is an entity, so it fits
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        pollRepository.delete(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
