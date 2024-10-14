package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.dto.MessageDTO;
import com.soccer.soccerTeamTalk.playload.request.MessageRequest;
import com.soccer.soccerTeamTalk.security.sercives.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/soccer/message")
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @PostMapping("/createmessage")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageRequest request) {
        MessageDTO createMessage = messageService.createMessage(request);
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
