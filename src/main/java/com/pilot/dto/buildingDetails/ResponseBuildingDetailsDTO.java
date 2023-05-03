package com.pilot.dto.buildingDetails;

import com.pilot.dto.buildingCoordinate.ResponseBuildingCoordinateDTO;
import com.pilot.entties.BuildingDetails;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBuildingDetailsDTO {

    private long id;
    private String type;
    private String geometryType;
    private Long idBuilding;
    private String buildingName;
    private Long idCoordinateHashCodes;
    private List<ResponseBuildingCoordinateDTO> buildingCoordinates = new ArrayList<>();
    private String properties;

    public static class Mapper implements Function<BuildingDetails, ResponseBuildingDetailsDTO> {
        @Override
        public ResponseBuildingDetailsDTO apply(BuildingDetails buildingDetails) {
            return ResponseBuildingDetailsDTO.builder()
                    .id(buildingDetails.getId())
                    .type(buildingDetails.getType())
                    .geometryType(buildingDetails.getGeometryType())
                    .idBuilding(buildingDetails.getBuilding() != null ? buildingDetails.getBuilding().getId() : null)
                    .buildingName(buildingDetails.getBuilding() != null ? buildingDetails.getBuilding().getName() : null)
                    .idCoordinateHashCodes(buildingDetails.getCoordinateHashCodes() != null ? buildingDetails.getCoordinateHashCodes().getId() : null)
                    .buildingCoordinates(buildingDetails.getCoordinateHashCodes() != null ?
                            buildingDetails.getCoordinateHashCodes().getBuildingCoordinates().stream()
                                    .map(coordinate -> ResponseBuildingCoordinateDTO.builder()
                                            .id(coordinate.getId())
                                            .latitude(coordinate.getLatitude())
                                            .longitude(coordinate.getLongitude())
                                            .build()).collect(Collectors.toList())
                            : null)
                    .build();
        }
    }

}
