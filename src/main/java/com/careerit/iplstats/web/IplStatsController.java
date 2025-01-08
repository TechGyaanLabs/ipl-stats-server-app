package com.careerit.iplstats.web;

import com.careerit.iplstats.dto.IplTeamStatsDto;
import com.careerit.iplstats.dto.TeamStatsDto;
import com.careerit.iplstats.service.IplStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iplstats")
@RequiredArgsConstructor
public class IplStatsController {

    private final IplStatsService iplStatsService;

    @GetMapping("/teamnames")
    public List<String> getTeamNames(){
        return iplStatsService.getTeamNames();
    }

    @GetMapping("/stats/{teamName}")
    public TeamStatsDto getTeamStats(@PathVariable String teamName){
        return iplStatsService.getTeamStats(teamName);
    }

    @GetMapping("/teamstats")
    public List<IplTeamStatsDto> getIplTeamStats(){
        return iplStatsService.getIplTeamStats();
    }


}
