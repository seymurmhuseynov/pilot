package com.pilot.services;

import com.pilot.dto.poi.RequestPoiDTO;
import com.pilot.models.ResponseData;

public interface PoiServices {
    ResponseData<?> insert(RequestPoiDTO requestPoiDTO);
    ResponseData<?> update(RequestPoiDTO requestPoiDTO);
    ResponseData<?> select();
    ResponseData<?> selectById(long id);
}
