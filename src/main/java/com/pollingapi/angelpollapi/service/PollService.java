package com.pollingapi.angelpollapi.service;

import com.pollingapi.angelpollapi.domain.Option;
import com.pollingapi.angelpollapi.domain.Poll;
import com.pollingapi.angelpollapi.exception.ResourceNotFoundException;
import com.pollingapi.angelpollapi.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    public Optional<Poll> getPoll(Long pollId) {
        verifyPoll(pollId);
        //Optional<Poll> p = pollRepository.findById(pollId); //findById method is (Optional<T> findById(ID id);). I need an object of type Optional<Poll> and not just Poll.
        return pollRepository.findById(pollId);
        //return new ResponseEntity<>(p, HttpStatus.OK);
    }

    public ResponseEntity<?> createPoll(Poll poll){
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders(); //response header is sent by server to client and provides additional information about response, for example content-type header would specify the type of content that the server is sending
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri(); //uri is to identify resources that can be accessed over a network, it requires a scheme(http://) and a path(/{id})
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED); //this will replace the path variable with the variable passed in
    }

    public ResponseEntity<?> updatePoll(Poll poll, Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll); // .save needs an entity parameter, which Poll is an entity, so it fits
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePoll(Long pollId){
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll.isEmpty()){ // if the poll given does not exist it will return the message below
            throw new ResourceNotFoundException("The poll with id " + pollId + " does not exist");
        }
    }
}
