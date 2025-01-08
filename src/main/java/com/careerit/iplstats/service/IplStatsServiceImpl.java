package com.careerit.iplstats.service;

import com.careerit.iplstats.domain.Player;
import com.careerit.iplstats.dto.*;
import com.careerit.iplstats.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<IplTeamStatsDto> getIplTeamStats() {
        if(players.isEmpty()){
            throw new IllegalArgumentException("No players found");
        }
        List<IplTeamStatsDto> iplTeamStatsDtos = new ArrayList<>();

        Map<String, List<Player>> teamPlayersMap = players.stream().collect(Collectors.groupingBy(Player::getTeam));
        for(Map.Entry<String,List<Player>> entry : teamPlayersMap.entrySet()){
            String teamName = entry.getKey();
            List<Player> teamPlayers = entry.getValue();
            int totalPlayers = teamPlayers.size();
            double totalAmount = teamPlayers.stream().mapToDouble(Player::getAmount).sum();
            TeamAmountDto teamAmountDto = new TeamAmountDto(teamName,totalAmount);
            PlayerCountDto playerCountDto = new PlayerCountDto(teamName,totalPlayers);
            IplTeamStatsDto iplTeamStatsDto = new IplTeamStatsDto(teamAmountDto,playerCountDto);
            iplTeamStatsDtos.add(iplTeamStatsDto);
        }
        return iplTeamStatsDtos;
    }
}
