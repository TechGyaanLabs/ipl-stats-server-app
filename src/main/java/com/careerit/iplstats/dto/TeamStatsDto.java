package com.careerit.iplstats.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TeamStatsDto {

    private List<PlayerDto> players;
    private Map<String,Integer> roleCount;
    private Map<String,Integer> countryCount;

}
