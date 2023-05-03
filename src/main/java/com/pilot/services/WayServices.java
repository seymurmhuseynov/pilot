package com.pilot.services;

import com.pilot.dto.way.RequestWayDTO;
import com.pilot.models.ResponseData;

public interface WayServices {
    ResponseData<?> insert(RequestWayDTO requestWayDTO);
    ResponseData<?> update(RequestWayDTO requestWayDTO);
    ResponseData<?> select();
    ResponseData<?> selectById(long id);
}
