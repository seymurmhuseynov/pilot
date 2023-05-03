package com.pilot.dto.coordinate;

import com.pilot.entties.Coordinate;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCoordinateDTO {

    private long id;
    private double latitude;
    private double longitude;

    public static class Mapper implements Function<Coordinate, ResponseCoordinateDTO> {

        @Override
        public ResponseCoordinateDTO apply(Coordinate coordinate) {
            return ResponseCoordinateDTO.builder()
                    .id(coordinate.getId())
                    .latitude(coordinate.getLatitude())
                    .longitude(coordinate.getLongitude())
                    .build();
        }
    }

}
