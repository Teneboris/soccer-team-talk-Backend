package com.soccer.soccerTeamTalk.playload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
@SuperBuilder
public class MessageResponse {

    private String message;

    private HttpStatus status;

    private Map<?, ?> data;

    protected int statusCode;
}
