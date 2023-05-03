package com.pilot.dto.poi;

import com.pilot.entties.Poi;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPoiDTO {
    private long id;
    private String type;
    private String geometryType;
    private List<List<List<String>>> coordinates=new ArrayList<>();
    private String properties;

    public static class Mapper implements Function<RequestPoiDTO, Poi> {
        @Override
        public Poi apply(RequestPoiDTO dto) {
            return Poi.builder()
                    .id(dto.getId())
                    .type(dto.getType())
                    .geometryType(dto.getGeometryType())
                    .properties(dto.getProperties())
                    .build();
        }
    }
}
