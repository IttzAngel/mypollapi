package com.pollingapi.angelpollapi.repositories;

import com.pollingapi.angelpollapi.domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

}
