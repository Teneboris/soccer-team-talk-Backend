package com.soccer.soccerTeamTalk.playload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@SuperBuilder
public class MessageResponse {

    private String message;

    private HttpStatus status;

    public MessageResponse(String message){
        this.message = message;
    }
}
