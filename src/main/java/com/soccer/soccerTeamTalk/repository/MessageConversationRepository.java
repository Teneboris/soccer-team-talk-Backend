package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.conversation.MessageConversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageConversationRepository extends MongoRepository<MessageConversation, String> {
}
