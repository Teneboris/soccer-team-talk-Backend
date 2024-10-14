package com.soccer.soccerTeamTalk.models.conversation;

import jdk.javadoc.doclet.Taglet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageType {

    private String text;

    private String image;

    private String video;

    private String audio;

    private String file;

    private Taglet.Location location;
}
