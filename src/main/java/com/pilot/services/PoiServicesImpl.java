package com.pilot.services;

import com.pilot.dto.poi.RequestPoiDTO;
import com.pilot.dto.poi.ResponsePoiDTO;
import com.pilot.entties.Coordinate;
import com.pilot.entties.Poi;
import com.pilot.exceptions.NotFoundException;
import com.pilot.models.ResponseData;
import com.pilot.repos.PoiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PoiServicesImpl implements PoiServices {
    private PoiRepository poiRepository;

    @Override
    public ResponseData<?> insert(RequestPoiDTO requestPoiDTO) {
        Poi poi = Optional.of(requestPoiDTO)
                .map(new RequestPoiDTO.Mapper())
                .orElseThrow(NotFoundException::new);

        poi.setType(requestPoiDTO.getType());
        poi.setGeometryType(requestPoiDTO.getGeometryType());
        poi.setProperties(requestPoiDTO.getProperties());

        poi.setCoordinates(requestPoiDTO.getCoordinates().stream()
                .flatMap(Collection::stream)
                .map(cord -> Coordinate.builder()
                        .latitude(Double.parseDouble(cord.get(0)))
                        .longitude(Double.parseDouble(cord.get(1)))
                        .build()).toList());

        poiRepository.save(poi);
        return ResponseData.ok(poi.getId());
    }

    @Override
    @Transactional
    public ResponseData<?> update(RequestPoiDTO requestPoiDTO) {
        poiRepository.findById(requestPoiDTO.getId())
                .map(way -> {
                    way.setType(requestPoiDTO.getType());
                    way.setGeometryType(requestPoiDTO.getGeometryType());
                    way.setProperties(requestPoiDTO.getProperties());
                    way.setCoordinates(requestPoiDTO.getCoordinates().stream()
                            .flatMap(Collection::stream)
                            .map(cord -> Coordinate.builder()
                                    .latitude(Double.parseDouble(cord.get(0)))
                                    .longitude(Double.parseDouble(cord.get(1)))
                                    .build()).toList());

                    return way;
                }).orElseThrow(NotFoundException::new);
        return ResponseData.ok();

    }

    @Override
    public ResponseData<?> select() {
        return ResponseData.ok(poiRepository.findAll()
                .stream()
                .map(new ResponsePoiDTO.Mapper())
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseData<?> selectById(long id) {
        return ResponseData.ok(poiRepository.findById(id)
                .stream()
                .map(new ResponsePoiDTO.Mapper())
                .collect(Collectors.toList()));
    }
}
