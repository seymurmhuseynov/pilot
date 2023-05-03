package com.pilot.repos;

import com.pilot.entties.Poi;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PoiRepository extends CrudRepository<Poi,Long>, JpaSpecificationExecutor<Poi> {
    List<Poi> findAll();
}
