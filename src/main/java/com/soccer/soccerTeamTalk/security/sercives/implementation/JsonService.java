package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JsonService {

    private final ObjectMapper objectMapper;

    public JsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<String, String> deserializerJsonToMap(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});
        }catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON ton Map", e);
        }
    }

}
