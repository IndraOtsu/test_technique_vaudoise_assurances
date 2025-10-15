package com.marcos.vaudoise.model.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PersonDTO {

    private String name;

    private String phone;

    private String email;

    @JsonProperty("birth_date")
    private String birthDate;
}
