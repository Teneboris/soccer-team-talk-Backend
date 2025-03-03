package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.models.conversation.Message;
import com.soccer.soccerTeamTalk.security.sercives.implementation.MessageConversationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/soccer/messageConversation")
public class MessageConversationController {

    @Autowired
    public MessageConversationServiceImpl messageConversationService;

    @GetMapping("/fetchConversationBetweenTwoTeammate/{trainingId}/{senderUsername}/{recipientUsername}")
    public ResponseEntity<List<Message>> fetchConversationBetweenTwoOpponent(@PathVariable String senderUsername, @PathVariable String recipientUsername, @PathVariable String trainingId) {
        List<Message> messages = messageConversationService.fetchConversationBetweenTwoTeammate(senderUsername, recipientUsername, trainingId);
        return ResponseEntity.ok(messages);
    }
}
