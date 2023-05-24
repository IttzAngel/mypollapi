package com.pollingapi.angelpollapi.controller;

import com.pollingapi.angelpollapi.dto.OptionCount;
import com.pollingapi.angelpollapi.dto.VoteResult;
import com.pollingapi.angelpollapi.domain.Vote;
import com.pollingapi.angelpollapi.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;

}
