package com.soccer.soccerTeamTalk.playload.request;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.MessageContent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MessageRequest {

    private MessageContent content;
    private User sender;
    private User recipient;
    private Set<String> status;

}
