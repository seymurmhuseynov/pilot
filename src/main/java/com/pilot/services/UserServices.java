package com.pilot.services;

import com.pilot.models.RequestLogin;
import com.pilot.models.ResponseData;

public interface UserServices {
    ResponseData<?> insert(RequestLogin requestLogin);
}
