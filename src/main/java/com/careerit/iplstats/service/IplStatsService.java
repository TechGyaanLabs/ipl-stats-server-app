package com.careerit.iplstats.service;

import com.careerit.iplstats.dto.IplTeamStatsDto;
import com.careerit.iplstats.dto.TeamStatsDto;

import java.util.List;

public interface IplStatsService {

        List<String> getTeamNames();
        TeamStatsDto getTeamStats(String teamName);
        List<IplTeamStatsDto> getIplTeamStats();

}
