package com.marcos.vaudoise.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractSumDTO {
    @JsonProperty("total_cost")
    private float totalCost;
    @JsonProperty("client_id")
    private UUID clientId;
}
