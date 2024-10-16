package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.dto.MessageDTO;
import com.soccer.soccerTeamTalk.models.conversation.Message;
import com.soccer.soccerTeamTalk.playload.request.MessageRequest;

import java.util.List;

public interface MessageService {

    MessageDTO createMessage(MessageRequest request, String id);

    List<MessageDTO> getAllMessages();

    MessageDTO getMessageById(String id);
}
