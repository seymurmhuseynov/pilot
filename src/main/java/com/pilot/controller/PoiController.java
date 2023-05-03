package com.pilot.controller;

import com.pilot.dto.poi.RequestPoiDTO;
import com.pilot.models.ResponseData;
import com.pilot.services.PoiServices;
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
public class PoiController {

    private PoiServices poiServices;

    @PostMapping("/poi")
    public ResponseData<?> insert(@RequestBody @Valid @NotNull RequestPoiDTO requestPoiDTO) {
        return poiServices.insert(requestPoiDTO);
    }

    @PutMapping("/poi")
    public ResponseData<?> update(@RequestBody @Valid @NotNull RequestPoiDTO requestPoiDTO) {
        return poiServices.update(requestPoiDTO);
    }
    @GetMapping("/poises")
    public ResponseData<?> selectAll() {
        return poiServices.select();
    }

    @GetMapping("/poi/{id}")
    public ResponseData<?> selectById(@PathVariable @Valid @Min(1) long id) {
        return poiServices.selectById(id);
    }

}
