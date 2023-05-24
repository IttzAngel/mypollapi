package com.pollingapi.angelpollapi.controller;

import com.pollingapi.angelpollapi.domain.Vote;
import com.pollingapi.angelpollapi.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
        vote = voteRepository.save(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri()); // setLocation sets the location and specifies the url, fromCurrentRequest creates an SUCB instance from the current request (in the name) path selects the path we want it to be in, buildAndExpand replaces the placeholder with the voteId value and toUri converts the Uri that we just built
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED); //this shows if it was correctly created
    }



}
