package com.soccer.soccerTeamTalk.playload.request;

import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.MessageContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private MessageContent content;
    private User recipient;
    private User sender;
    private Set<String> status;
    private Training training;

}
