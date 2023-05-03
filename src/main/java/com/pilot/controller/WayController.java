package com.pilot.controller;

import com.pilot.dto.way.RequestWayDTO;
import com.pilot.models.ResponseData;
import com.pilot.services.WayServices;
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
public class WayController {

    private WayServices wayServices;

    @PostMapping("/way")
    public ResponseData<?> insert(@RequestBody @Valid @NotNull RequestWayDTO requestWayDTO) {
        return wayServices.insert(requestWayDTO);
    }

    @PutMapping("/way")
    public ResponseData<?> update(@RequestBody @Valid @NotNull RequestWayDTO requestWayDTO) {
        return wayServices.update(requestWayDTO);
    }
    @GetMapping("/ways")
    public ResponseData<?> selectAll() {
        return wayServices.select();
    }

    @GetMapping("/way/{id}")
    public ResponseData<?> selectById(@PathVariable @Valid @Min(1) long id) {
        return wayServices.selectById(id);
    }

}
