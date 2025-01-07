package com.careerit.iplstats.dto;

import lombok.Data;

import java.util.Map;

@Data
public class IplTeamStatsDto {
    private Map<String,Double> teamAmount;
    private Map<String,Integer> playerCount;
}
