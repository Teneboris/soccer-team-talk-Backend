package com.soccer.soccerTeamTalk.models.conversation;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {

    private String text;

    private String image;

    private String location;

    private String file;

    private String audio;

}
