package com.soccer.soccerTeamTalk.models.conversation;

import com.soccer.soccerTeamTalk.audit.Auditable;
import com.soccer.soccerTeamTalk.dto.TrainingGameDTO;
import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Document(collection="messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends Auditable<String> {

    @Id
    private String id;

    //private String type;

    private MessageContent content;

    //private String channelId;

    //private Object source;

    //private String eventType;

    //private String reportUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserDetails sender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;

    private Set<MessageStatus> status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Training training;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    private TrainingGameDTO trainingGameDTO;

    private MessageConversation conversation;

}
