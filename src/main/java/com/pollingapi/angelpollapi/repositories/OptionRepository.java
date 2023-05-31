package com.pollingapi.angelpollapi.repositories;

import com.pollingapi.angelpollapi.domain.Option;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

    @Query(value="select o.* from Option o where o.POLL_ID = ?1", nativeQuery = true)
    public Iterable<Option> findByPoll(Long pollId);

}
