package com.careerit.iplstats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IplTeamStatsDto {
    private TeamAmountDto teamAmount;
    private PlayerCountDto playerCount;
}
