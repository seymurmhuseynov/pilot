package com.pilot.dto.buildingDetails;

import com.pilot.entties.BuildingDetails;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestBuildingDetailsDTO {

    private long id;
    private String type;
    private String geometryType;
    private List<List<List<String>>> coordinates=new ArrayList<>();
    private long idBuilding;
    private String properties;

    public static class Mapper implements Function<RequestBuildingDetailsDTO, BuildingDetails> {
        @Override
        public BuildingDetails apply(RequestBuildingDetailsDTO dto) {
            return BuildingDetails.builder()
                    .id(dto.getId())
                    .type(dto.getType())
                    .geometryType(dto.getGeometryType())
                    .properties(dto.getProperties())
                    .build();
        }
    }
}
