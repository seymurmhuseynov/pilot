package com.pilot.repos;

import com.pilot.entties.BuildingDetails;
import com.pilot.entties.Way;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WayRepository extends CrudRepository<Way,Long>, JpaSpecificationExecutor<Way> {
    List<Way> findAll();

}
