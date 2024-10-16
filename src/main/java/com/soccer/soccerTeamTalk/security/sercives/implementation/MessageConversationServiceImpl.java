package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.conversation.MessageConversation;
import com.soccer.soccerTeamTalk.repository.MessageConversationRepository;
import com.soccer.soccerTeamTalk.repository.MessageRepository;
import com.soccer.soccerTeamTalk.security.sercives.MessageConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConversationServiceImpl implements MessageConversationService {

    @Autowired
    MessageConversationRepository messageConversationRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageConversation createNewConversation(MessageConversation message) {
        return messageConversationRepository.save(message);
    }

    @Override
    public MessageConversation sendingMessage(MessageConversation message) {

        return null;
    }

    @Override
    public MessageConversation receivingMessage() {
        return null;
    }

    @Override
    public MessageConversation retrievingConversationHistory() {
        return null;
    }

    @Override
    public MessageConversation SearchingForConversations() {
        return null;
    }
}
