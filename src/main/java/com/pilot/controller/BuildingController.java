package com.pilot.controller;

import com.pilot.dto.buildingDetails.RequestBuildingDetailsDTO;
import com.pilot.models.ResponseData;
import com.pilot.services.BuildingDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BuildingController {

    private BuildingDetailsServices buildingDetailsServices;

    @PostMapping("/buildings")
    public ResponseData<?> insert(@RequestBody @Valid @NotNull RequestBuildingDetailsDTO requestBuildingDetailsDTO) {
        return buildingDetailsServices.insert(requestBuildingDetailsDTO);
    }

    @PutMapping("/buildings")
    public ResponseData<?> update(@RequestBody @Valid @NotNull RequestBuildingDetailsDTO requestBuildingDetailsDTO) {
        return buildingDetailsServices.update(requestBuildingDetailsDTO);
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseData<?> delete(@PathVariable @Valid @Min(1) long id) {
        return buildingDetailsServices.delete(id);
    }

    @GetMapping("/buildings")
    public ResponseData<?> selectAll() {
        return buildingDetailsServices.select();
    }

    @GetMapping("/buildings/{idBuilding}")
    public ResponseData<?> selectByIdBuilding(@PathVariable @Valid @Min(1) long idBuilding) {
        return buildingDetailsServices.selectByIdBuilding(idBuilding);
    }

}
