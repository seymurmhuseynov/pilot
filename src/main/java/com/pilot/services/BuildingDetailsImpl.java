package com.pilot.services;

import com.pilot.dto.buildingDetails.RequestBuildingDetailsDTO;
import com.pilot.dto.buildingDetails.ResponseBuildingDetailsDTO;
import com.pilot.entties.BuildingCoordinate;
import com.pilot.entties.BuildingDetails;
import com.pilot.entties.CoordinateHashCodes;
import com.pilot.exceptions.NotFoundException;
import com.pilot.models.ResponseData;
import com.pilot.repos.BuildingDetailsRepository;
import com.pilot.repos.BuildingRepository;
import com.pilot.repos.CoordinateHashCodesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingDetailsImpl implements BuildingDetailsServices {
    private BuildingDetailsRepository buildingDetailsRepository;
    private CoordinateHashCodesRepository coordinateHashCodesRepository;
    private BuildingRepository buildingRepository;

    @Override
    public ResponseData<?> insert(RequestBuildingDetailsDTO requestBuildingDetailsDTO) {
        BuildingDetails buildingDetails = Optional.of(requestBuildingDetailsDTO)
                .map(new RequestBuildingDetailsDTO.Mapper())
                .orElseThrow(NotFoundException::new);

        int hashCode = requestBuildingDetailsDTO.getCoordinates().hashCode();

        buildingDetails.setCoordinateHashCodes(coordinateHashCodesRepository.findByHashCode(hashCode).orElse(
                CoordinateHashCodes.builder()
                        .buildingCoordinates(requestBuildingDetailsDTO.getCoordinates().stream()
                                .flatMap(Collection::stream)
                                .map(cord -> BuildingCoordinate.builder()
                                        .latitude(Double.parseDouble(cord.get(0)))
                                        .longitude(Double.parseDouble(cord.get(1)))
                                        .build()).toList())
                        .hashCode(hashCode)
                        .build()
        ));

        buildingDetails.setBuilding(buildingRepository.findById(requestBuildingDetailsDTO.getIdBuilding()).orElse(null));
        buildingDetails.setProperties(buildingDetails.getProperties());
        buildingDetails.setDeleted(false);

        buildingDetailsRepository.save(buildingDetails);
        return ResponseData.ok(buildingDetails.getId());
    }

    @Override
    @Transactional
    public ResponseData<?> update(RequestBuildingDetailsDTO requestBuildingDetailsDTO) {
        buildingDetailsRepository.findById(requestBuildingDetailsDTO.getId())
                .map(buildingDetails -> {
                    buildingDetails.setType(requestBuildingDetailsDTO.getType());
                    buildingDetails.setGeometryType(requestBuildingDetailsDTO.getGeometryType());

                    int hashCode = requestBuildingDetailsDTO.getCoordinates().hashCode();
                    System.out.println("hashCode = " + hashCode);

                    buildingDetails.setCoordinateHashCodes(coordinateHashCodesRepository.findByHashCode(hashCode).orElse(
                            CoordinateHashCodes.builder()
                                    .buildingCoordinates(requestBuildingDetailsDTO.getCoordinates().stream()
                                            .flatMap(Collection::stream)
                                            .map(cord -> BuildingCoordinate.builder()
                                                    .latitude(Double.parseDouble(cord.get(0)))
                                                    .longitude(Double.parseDouble(cord.get(1)))
                                                    .build()).toList())
                                    .hashCode(hashCode)
                                    .build()
                    ));

                    buildingDetails.setBuilding(buildingRepository.findById(requestBuildingDetailsDTO.getIdBuilding()).orElse(null));
                    buildingDetails.setProperties(requestBuildingDetailsDTO.getProperties());

                    return buildingDetails;
                }).orElseThrow(NotFoundException::new);
        return ResponseData.ok();
    }

    @Override
    public ResponseData<?> select() {
        return ResponseData.ok(buildingDetailsRepository.findAllByDeletedIsFalse()
                .stream()
                .map(new ResponseBuildingDetailsDTO.Mapper())
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseData<?> selectByIdBuilding(long id) {
        return ResponseData.ok(buildingDetailsRepository.findAllByBuilding_IdAndDeletedIsFalse(id)
                .stream()
                .map(new ResponseBuildingDetailsDTO.Mapper())
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResponseData<?> delete(long id) {
        buildingDetailsRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseData.ok();
    }
}