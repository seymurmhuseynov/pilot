package com.pilot.dto.poi;

import com.pilot.dto.coordinate.ResponseCoordinateDTO;
import com.pilot.entties.Poi;
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
public class ResponsePoiDTO {
    private long id;
    private String type;
    private String geometryType;
    private String properties;
    private List<ResponseCoordinateDTO> coordinates = new ArrayList<>();
    public static class Mapper implements Function<Poi, ResponsePoiDTO> {
        @Override
        public ResponsePoiDTO apply(Poi poi) {
            return ResponsePoiDTO.builder()
                    .id(poi.getId())
                    .type(poi.getType())
                    .geometryType(poi.getGeometryType())
                    .coordinates(poi.getCoordinates().stream()
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
