package com.pollingapi.angelpollapi.service;

import com.pollingapi.angelpollapi.domain.Option;
import com.pollingapi.angelpollapi.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PollService {

    private Long id;
    private Set<Option> options;
    @Autowired
    private PollRepository pollRepository;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
