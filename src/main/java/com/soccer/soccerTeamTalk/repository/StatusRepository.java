package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.conversation.EMessageStatus;
import com.soccer.soccerTeamTalk.models.conversation.Message;
import com.soccer.soccerTeamTalk.models.conversation.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatusRepository extends MongoRepository<MessageStatus, String> {
    Optional<MessageStatus> findByStatus(EMessageStatus status);
}
