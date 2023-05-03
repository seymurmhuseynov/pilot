package com.pilot.dto.way;

import com.pilot.entties.Way;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWayDTO {
    private long id;
    private String type;
    private String geometryType;
    private List<List<List<String>>> coordinates=new ArrayList<>();
    private String properties;

    public static class Mapper implements Function<RequestWayDTO, Way> {
        @Override
        public Way apply(RequestWayDTO dto) {
            return Way.builder()
                    .id(dto.getId())
                    .type(dto.getType())
                    .geometryType(dto.getGeometryType())
                    .properties(dto.getProperties())
                    .build();
        }
    }
}
