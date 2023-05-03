package com.pilot.repos;

import com.pilot.entties.BuildingDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingDetailsRepository extends CrudRepository<BuildingDetails,Long> , JpaSpecificationExecutor<BuildingDetails> {

    List<BuildingDetails> findAllByDeletedIsFalse();
    List<BuildingDetails> findAllByBuilding_IdAndDeletedIsFalse(long id);

}
