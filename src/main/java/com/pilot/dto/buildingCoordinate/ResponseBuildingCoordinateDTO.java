package com.pilot.dto.buildingCoordinate;

import com.pilot.entties.BuildingCoordinate;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBuildingCoordinateDTO {

    private long id;
    private double latitude;
    private double longitude;

    public static class Mapper implements Function<BuildingCoordinate, ResponseBuildingCoordinateDTO> {

        @Override
        public ResponseBuildingCoordinateDTO apply(BuildingCoordinate coordinate) {
            return ResponseBuildingCoordinateDTO.builder()
                    .id(coordinate.getId())
                    .latitude(coordinate.getLatitude())
                    .longitude(coordinate.getLongitude())
                    .build();
        }
    }

}
