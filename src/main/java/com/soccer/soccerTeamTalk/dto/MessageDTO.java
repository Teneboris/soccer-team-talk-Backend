package com.soccer.soccerTeamTalk.dto;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.MessageContent;
import com.soccer.soccerTeamTalk.models.conversation.MessageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class MessageDTO {

    private MessageContent content;

    private String sender;

    private User recipient;

    private Set<MessageStatus> status;

    private String training;

    private String Game;

    public MessageDTO(MessageContent content, String sender, User recipient, Set<MessageStatus> status,String training, String game) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.status = status;
        this.training = training;
        this.Game = game;
    }
}
