package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.dto.MessageDTO;
import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.models.conversation.*;
import com.soccer.soccerTeamTalk.playload.request.MessageRequest;
import com.soccer.soccerTeamTalk.repository.*;
import com.soccer.soccerTeamTalk.security.jwt.AuthEntryPointJwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private TrainingServiceImpl trainingService;

    @Autowired
    private GameServiceImpl gameService;


    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public MessageDTO createMessage(MessageRequest request, String id) {

        Message message = new Message();
        //checkIfConversationExist();
        message.setContent(setMessageContent(request));
        message.setSender(setSender(request));
        message.setRecipient(setRecipient(request));
        message.setStatus(chooseStatus(request));
        message.setTraining(setTrainingId(id));
        message.setGame(setGameId(id));

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

    private UserDetails setSender(MessageRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getSender().getUsername());

        if (userDetails == null) {
            throw new RuntimeException("No valid senders found");
        }

        if(userDetails.getUsername().equals(request.getSender().getUsername())){
            request.getSender().setUsername(userDetails.getUsername());
        }
        return userDetails;
    }

/*    private String checkToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String jwt = jwtUtils.generateToken(userDetails);

        if(userDetails != null){
            return jwt;
        }
        throw new RuntimeException("registered user not identified as sender");
    }*/

    private User setRecipient(MessageRequest request) {

        List<User> validRecipients = getValidUsers();

        if (validRecipients.isEmpty()) {
            throw new RuntimeException("No valid recipients found in the usernames list");
        }

        for (int i=0; i<=validRecipients.size(); i++) {
            if(request.getRecipient().getUsername().equals(validRecipients.get(i).getUsername())) {
                request.getRecipient().setUsername(validRecipients.get(i).getUsername());
                return validRecipients.get(i);
            }
        }
        throw new RuntimeException("recipient cannot be set");
    }

    private Training setTrainingId(String id) {

        Optional<Training> validTrainingId = getTrainingIds(id);

        if (validTrainingId.isEmpty()) {
            throw new RuntimeException("valid training id not found");
        }

        Training training = new Training();

        if(validTrainingId.isPresent()) {
            training.setId(validTrainingId.get().getId());
        }
        return validTrainingId.get();
    }

    private Optional<Training> getTrainingIds(String id) {
        Optional<Training> trainingId = trainingService.getTrainingById(id);
        trainingId.stream()
                .filter(training -> training.getId() != null)
                .collect(Collectors.toList());
        return trainingId;
    }

    private Game setGameId(String id) {

        Optional<Game> validGameId = getGameIds(id);

        if (validGameId.isEmpty()) {
            throw new RuntimeException("valid training id not found");
        }

        Training training = new Training();

        if(validGameId.isPresent()) {
            training.setId(validGameId.get().getId());
        }
        return validGameId.get();
    }

    private Optional<Game> getGameIds(String id) {
        Optional<Game> gameId = gameService.getGameById(id);
        gameId.stream()
                .filter(game -> game.getId() != null)
                .collect(Collectors.toList());
        return gameId;
    }

    private List<User> getValidUsers() {

        List<User> usernames = getUsers();
        List<User> validUsers = usernames.stream()
                .filter(user -> user.getUsername() != null)
                .collect(Collectors.toList());
        return validUsers;
    }

    private List<User> getUsers() {
        List<User> user = new ArrayList<>(userRepository.findAll());
        return user;
    }

    private MessageContent setMessageContent(MessageRequest request) {
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
                message.getSender().getUsername(),
                message.getRecipient(),
                message.getStatus(),
                message.getTraining().getId(),
                message.getGame().getId()
        );
    }
}
