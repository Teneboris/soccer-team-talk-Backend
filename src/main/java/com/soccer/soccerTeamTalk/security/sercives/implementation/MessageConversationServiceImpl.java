package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.conversation.Message;
import com.soccer.soccerTeamTalk.models.conversation.MessageConversation;
import com.soccer.soccerTeamTalk.repository.MessageConversationRepository;
import com.soccer.soccerTeamTalk.repository.MessageRepository;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import com.soccer.soccerTeamTalk.security.sercives.MessageConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageConversationServiceImpl implements MessageConversationService {

    @Autowired
    MessageConversationRepository messageConversationRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public MessageConversation createNewConversation(MessageConversation message) {
        return messageConversationRepository.save(message);
    }

    public List<Message> fetchConversationBetweenTwoTeammate(String senderUsername, String recipientUsername, String trainingId) {
        List<Message> messages = messageRepository.findAll();

        return messages.stream()
                .filter(message -> {

                  boolean isBetweenTeammates =  (message.getSender().getUsername().equals(senderUsername) && message.getRecipient().getUsername().equals(recipientUsername)) ||
                            (message.getSender().getUsername().equals(recipientUsername) && message.getRecipient().getUsername().equals(senderUsername)) &&
                                    (message.getTraining().getId().equals(trainingId));
                  boolean isTrainingMatch = message.getTraining() != null && message.getTraining().getId().equals(trainingId);

                  return isBetweenTeammates && isTrainingMatch;
                })
                .collect(Collectors.toList());
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
