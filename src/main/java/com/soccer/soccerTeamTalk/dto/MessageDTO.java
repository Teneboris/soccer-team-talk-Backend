package com.soccer.soccerTeamTalk.dto;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.MessageContent;
import com.soccer.soccerTeamTalk.models.conversation.MessageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class MessageDTO {

    private MessageContent content;

    private String sender;

    private String recipient;

    private Set<MessageStatus> status;

    public MessageDTO(MessageContent content, String sender, String recipient, Set<MessageStatus> status) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.status = status;
    }
}
