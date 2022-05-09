package com.mangkyu.requestbody.app;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class User {

    @NotBlank
    private String name;

    @NotBlank
    private String desc;

}
