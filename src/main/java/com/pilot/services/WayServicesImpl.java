package com.pilot.services;

import com.pilot.dto.way.RequestWayDTO;
import com.pilot.dto.way.ResponseWayDTO;
import com.pilot.entties.Coordinate;
import com.pilot.entties.Way;
import com.pilot.exceptions.NotFoundException;
import com.pilot.models.ResponseData;
import com.pilot.repos.CoordinateRepository;
import com.pilot.repos.WayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WayServicesImpl implements WayServices {

    private WayRepository wayRepository;
    private CoordinateRepository coordinateRepository;
    @Override
    public ResponseData<?> insert(RequestWayDTO requestWayDTO) {
        Way way = Optional.of(requestWayDTO)
                .map(new RequestWayDTO.Mapper())
                .orElseThrow(NotFoundException::new);

        way.setType(requestWayDTO.getType());
        way.setGeometryType(requestWayDTO.getGeometryType());
        way.setProperties(requestWayDTO.getProperties());
        way.setCoordinates(requestWayDTO.getCoordinates().stream()
                .flatMap(Collection::stream)
                .map(cord -> Coordinate.builder()
                        .latitude(Double.parseDouble(cord.get(0)))
                        .longitude(Double.parseDouble(cord.get(1)))
                        .build()).toList());

        wayRepository.save(way);
        return ResponseData.ok(way.getId());
    }

    @Override
    @Transactional
    public ResponseData<?> update(RequestWayDTO requestWayDTO) {
        wayRepository.findById(requestWayDTO.getId())
                .map(way -> {
                    way.setType(requestWayDTO.getType());
                    way.setGeometryType(requestWayDTO.getGeometryType());
                    way.setProperties(requestWayDTO.getProperties());
                    way.setCoordinates(requestWayDTO.getCoordinates().stream()
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
        return ResponseData.ok(wayRepository.findAll()
                .stream()
                .map(new ResponseWayDTO.Mapper())
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseData<?> selectById(long id) {
        return ResponseData.ok(wayRepository.findById(id)
                .stream()
                .map(new ResponseWayDTO.Mapper())
                .collect(Collectors.toList()));
    }
}
