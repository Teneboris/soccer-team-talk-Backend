package com.soccer.soccerTeamTalk.models.conversation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageContent {

    private String text;

    private String image;

    private String location;

    private String file;

    private String audio;
}
