package com.pilot.services;

import com.pilot.dto.buildingDetails.RequestBuildingDetailsDTO;
import com.pilot.models.ResponseData;

public interface BuildingDetailsServices {
    ResponseData<?> insert(RequestBuildingDetailsDTO requestBuildingDetailsDTO);
    ResponseData<?> update(RequestBuildingDetailsDTO requestBuildingDetailsDTO);
    ResponseData<?> select();
    ResponseData<?> selectByIdBuilding(long id);
    ResponseData<?> delete(long id);

}
