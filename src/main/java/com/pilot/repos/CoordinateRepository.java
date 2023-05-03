package com.pilot.repos;

import com.pilot.entties.Coordinate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoordinateRepository extends CrudRepository<Coordinate,Long> , JpaSpecificationExecutor<Coordinate> {
    List<Coordinate> findAllByIdIn(List<Long> id);
}
