package com.pilot.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestLogin {

    @NotBlank
    @Size(min = 3, max = 60)
    private String email;
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
}
