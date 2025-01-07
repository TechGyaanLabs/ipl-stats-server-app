package com.careerit.iplstats.util;

import com.careerit.iplstats.domain.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class JsonUtil {

    private JsonUtil(){
        // Prevent instantiation
    }

    public static List<Player> loadPlayers(){
        String fileName = "/players.json";
        try{
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(JsonUtil.class.getResource(fileName),
                    new TypeReference<List<Player>>() {});
        }catch (Exception e){
            log.error("Error while loading players from file : {}",e.getMessage());
        }
        return List.of();
    }
}
