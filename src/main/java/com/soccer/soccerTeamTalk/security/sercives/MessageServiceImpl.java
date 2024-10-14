package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.dto.MessageDTO;
import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.*;
import com.soccer.soccerTeamTalk.playload.request.MessageRequest;
import com.soccer.soccerTeamTalk.repository.MessageConversationRepository;
import com.soccer.soccerTeamTalk.repository.MessageRepository;
import com.soccer.soccerTeamTalk.repository.StatusRepository;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import com.soccer.soccerTeamTalk.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageConversationRepository conversationRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public MessageDTO createMessage(MessageRequest request) {

        Message message = new Message();
        //checkIfConversationExist();
        message.setContent(getMessageContent(request));
        message.setSender(setSender(request));
        message.setRecipient(setRecipient(request));
        message.setStatus(chooseStatus(request));

        Message savedMessage = messageRepository.save(message);
        return mapToDTO(savedMessage);
    }

    private MessageConversation checkIfConversationExist(){
        MessageConversation messageConversation = new MessageConversation();
        if(messageConversation.getId() != null){
            return messageConversation.getMessage().getConversation();
        }
        throw new RuntimeException("conversation ID does not exist");
    }

    private User setSender(MessageRequest request) {

        List<User> validRecipients = getValidUsers();

        if (validRecipients.isEmpty()) {
            throw new RuntimeException("No valid senders found in the usernames list");
        }

        for (int i=0; i<=validRecipients.size(); i++) {
            if(request.getSender().getUsername().equals(validRecipients.get(i).getUsername())) {
                request.getSender().setUsername(validRecipients.get(i).getUsername());
                return validRecipients.get(i);
            }else {
                throw new RuntimeException("sender not found");
            }
        }
        throw new RuntimeException("sender cannot be set");
    }

    private User setRecipient(MessageRequest request) {

        List<User> validRecipients = getValidUsers();

        if (validRecipients.isEmpty()) {
            throw new RuntimeException("No valid recipients found in the usernames list");
        }

        for (int i=0; i<=validRecipients.size(); i++) {
            if(request.getRecipient().getUsername().equals(validRecipients.get(i).getUsername())) {
                request.getRecipient().setUsername(validRecipients.get(i).getUsername());
                return validRecipients.get(i);
            }else {
                throw new RuntimeException("recipient not found");
            }
        }
        throw new RuntimeException("recipient cannot be set");
    }

    private List<User> getValidUsers() {

        List<User> usernames = getUsersByUsername();
        List<User> validUsers = usernames.stream()
                .filter(user -> user.getUsername() != null)
                .collect(Collectors.toList());
        return validUsers;
    }

    private List<User> getUsersByUsername() {
        List<User> user = new ArrayList<>(userRepository.findAll());
        return user;
    }

    private MessageContent getMessageContent(MessageRequest request) {
        MessageContent content = new MessageContent();
        if(content.getText() == null){
            content.setText(request.getContent().getText());
        }
        return content;
    }

    private Set<MessageStatus> chooseStatus (MessageRequest messageRequest) {

        Set<String> strMessages = messageRequest.getStatus();
        Set<MessageStatus> messages = new HashSet<>();

        if (strMessages == null) {
            MessageStatus userRole = statusRepository.findByStatus(EMessageStatus.ACCEPTED)
                    .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
            messages.add(userRole);
        } else {
            strMessages.forEach(message -> {
                switch (message) {

                    case "read":
                        MessageStatus read = statusRepository.findByStatus(EMessageStatus.READ)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(read);
                        break;

                    case "delete":
                        MessageStatus deleted = statusRepository.findByStatus(EMessageStatus.DELETED)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(deleted);

                        break;

                    case "pending":
                        MessageStatus pending = statusRepository.findByStatus(EMessageStatus.PENDING)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(pending);

                        break;

                    case "sent":
                        MessageStatus sent = statusRepository.findByStatus(EMessageStatus.SENT)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(sent);

                        break;

                    case "failed":
                        MessageStatus failed = statusRepository.findByStatus(EMessageStatus.FAILED)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(failed);

                        break;
                    default:
                        MessageStatus userRole = statusRepository.findByStatus(EMessageStatus.ACCEPTED)
                                .orElseThrow(() -> new RuntimeException("Error: Status is not found."));
                        messages.add(userRole);
                }
            });
        }
        return messages;
    }

    private MessageConversation createConversation(User sender, User recipient) {

        MessageConversation conversation = new MessageConversation();
        MessageStatus status = new MessageStatus();
        conversation.setParticipants(Set.of(sender, recipient));
        conversation.setStatus(status);

        return conversationRepository.save(conversation);
    }

 /*   private MessageConversation getConversationById(String id) {
        Message messageUser = new Message();
        MessageConversation conversationId = conversationRepository.findById(id)
                .orElseGet(() -> createConversation(messageUser.getSender(), messageUser.getRecipient()));
            return conversationId;
    }
*/
    @Override
    public List<MessageDTO> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MessageDTO getMessageById(String id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found"));
        return mapToDTO(message);
    }

    /**
     * Helper method to map Message (entity) to MessageDTO
     * @param message
     * @return
     */
    private MessageDTO mapToDTO(Message message) {
        return new MessageDTO(
                message.getContent(),
                message.getSender().getEmail(),
                message.getRecipient().getEmail(),
                message.getStatus()
        );
    }
}
