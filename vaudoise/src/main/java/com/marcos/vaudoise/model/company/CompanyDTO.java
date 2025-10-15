package com.marcos.vaudoise.model.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDTO {

    private String name;

    private String phone;

    private String email;

    @JsonProperty("company_identifier")
    private String companyIdentifier;
}
