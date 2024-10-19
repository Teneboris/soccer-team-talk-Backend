package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.conversation.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

}
