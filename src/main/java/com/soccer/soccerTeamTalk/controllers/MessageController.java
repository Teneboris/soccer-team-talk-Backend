package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.dto.MessageDTO;
import com.soccer.soccerTeamTalk.playload.request.MessageRequest;
import com.soccer.soccerTeamTalk.security.sercives.implementation.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/soccer/message")
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @PostMapping("/{trainingId}/createmessage")
    public ResponseEntity<MessageDTO> createMessageForSpecificTraining(@RequestBody MessageRequest request, @PathVariable("trainingId") String trainingId) {
        MessageDTO createMessage = messageService.createMessage(request, trainingId);
        return ResponseEntity.ok(createMessage);
    }

    @PostMapping("/{gameId}/createmessage")
    public ResponseEntity<MessageDTO> createMessageForSpecificGame(@RequestBody MessageRequest request, @PathVariable("gameId") String gameId) {
        MessageDTO createMessage = messageService.createMessage(request, gameId);
        return ResponseEntity.ok(createMessage);
    }

    @GetMapping("/getallmessages")
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        List<MessageDTO> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("getmessageById/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable String id) {
        MessageDTO message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }
}
