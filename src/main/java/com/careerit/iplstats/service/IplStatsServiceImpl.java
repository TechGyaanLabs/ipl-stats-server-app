package com.careerit.iplstats.service;

import com.careerit.iplstats.domain.Player;
import com.careerit.iplstats.dto.IplTeamStatsDto;
import com.careerit.iplstats.dto.PlayerDto;
import com.careerit.iplstats.dto.TeamStatsDto;
import com.careerit.iplstats.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IplStatsServiceImpl implements IplStatsService {

    private final List<Player> players;

    public IplStatsServiceImpl(){
        this.players = JsonUtil.loadPlayers();
    }

    @Override
    public List<String> getTeamNames() {
       if(players.isEmpty()){
          log.info("No players found");
            return List.of();
       }
       List<String> teamNames = players.stream().map(Player::getTeam).distinct().toList();
       log.info("Total teams found : {}",teamNames.size());
       return teamNames;
    }

    @Override
    public TeamStatsDto getTeamStats(String teamName) {

        if(players.isEmpty()){
            throw new IllegalArgumentException("No players found");
        }

        List<Player> teamPlayers = players.stream()
                .filter(player -> player.getTeam().equalsIgnoreCase(teamName)).
                toList();
        List<PlayerDto> playerDtos = teamPlayers.stream()
                .map(player -> new PlayerDto(player.getName(),player.getRole(),player.getCountry(),player.getAmount()))
                .toList();

        Map<String,Integer> roleCount = teamPlayers.
                stream().collect(Collectors.groupingBy(Player::getRole,Collectors.summingInt(e -> 1)));

        Map<String,Integer> countryCount = teamPlayers.
                stream().collect(Collectors.groupingBy(Player::getCountry,Collectors.summingInt(e -> 1)));

        TeamStatsDto teamStatsDto = new TeamStatsDto();
        teamStatsDto.setPlayers(playerDtos);
        teamStatsDto.setRoleCount(roleCount);
        teamStatsDto.setCountryCount(countryCount);
        return teamStatsDto;

    }

    @Override
    public IplTeamStatsDto getIplTeamStats() {
        if(players.isEmpty()){
            throw new IllegalArgumentException("No players found");
        }
        Map<String,Double> teamAmount = players.stream()
                .collect(Collectors.groupingBy(Player::getTeam,Collectors.summingDouble(Player::getAmount)));

        Map<String,Integer> teamPlayerCount = players.stream()
                .collect(Collectors.groupingBy(Player::getTeam,Collectors.summingInt(e -> 1)));

        IplTeamStatsDto iplTeamStatsDto = new IplTeamStatsDto();
        iplTeamStatsDto.setTeamAmount(teamAmount);
        iplTeamStatsDto.setPlayerCount(teamPlayerCount);
        return iplTeamStatsDto;
    }
}
