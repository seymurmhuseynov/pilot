package com.pilot.repos;

import com.pilot.entties.Building;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingRepository extends CrudRepository<Building,Long> , JpaSpecificationExecutor<Building> {
    List<Building> findAll();
}
