package com.pollingapi.angelpollapi.repositories;

import com.pollingapi.angelpollapi.domain.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

}
