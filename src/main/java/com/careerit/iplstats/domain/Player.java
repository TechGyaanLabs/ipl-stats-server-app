package com.careerit.iplstats.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String name;
    private String role;
    private String country;
    @JsonProperty("price")
    private double amount;
    private String team;
}
