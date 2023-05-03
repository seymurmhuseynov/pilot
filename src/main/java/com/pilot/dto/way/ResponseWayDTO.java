package com.pilot.dto.way;

import com.pilot.dto.coordinate.ResponseCoordinateDTO;
import com.pilot.entties.Way;
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
public class ResponseWayDTO {
    private long id;
    private String type;
    private String geometryType;
    private String properties;
    private List<ResponseCoordinateDTO> coordinates = new ArrayList<>();
    public static class Mapper implements Function<Way, ResponseWayDTO> {
        @Override
        public ResponseWayDTO apply(Way way) {
            return ResponseWayDTO.builder()
                    .id(way.getId())
                    .type(way.getType())
                    .geometryType(way.getGeometryType())
                    .coordinates(way.getCoordinates().stream()
                            .map(coordinate -> ResponseCoordinateDTO.builder()
                                    .id(coordinate.getId())
                                    .latitude(coordinate.getLatitude())
                                    .longitude(coordinate.getLongitude())
                                    .build()).collect(Collectors.toList())
                    )
                    .build();
        }
    }
}
