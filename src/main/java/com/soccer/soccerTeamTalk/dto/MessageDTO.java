package com.soccer.soccerTeamTalk.dto;

import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.MessageContent;
import com.soccer.soccerTeamTalk.models.conversation.MessageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MessageDTO {

    private String messageId;
    private MessageContent content;

    private String sender;

    private User recipient;

    private Set<MessageStatus> status;

    private Training training;

    private Game game;

    public MessageDTO(String messageId, MessageContent content, String sender, User recipient, Set<MessageStatus> status,Training training, Game game) {
        this.messageId = messageId;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.status = status;
        this.training = training;
        this.game = game;
    }
}
