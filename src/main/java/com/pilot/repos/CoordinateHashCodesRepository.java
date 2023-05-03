package com.pilot.repos;

import com.pilot.entties.CoordinateHashCodes;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoordinateHashCodesRepository extends CrudRepository<CoordinateHashCodes,Long>, JpaSpecificationExecutor<CoordinateHashCodes> {
    Optional<CoordinateHashCodes> findByHashCode(int hashCode);
}
