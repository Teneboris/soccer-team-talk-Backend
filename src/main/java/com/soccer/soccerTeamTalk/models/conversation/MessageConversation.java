package com.soccer.soccerTeamTalk.models.conversation;

import com.soccer.soccerTeamTalk.audit.Auditable;
import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection="conversation")
@Getter
@Setter
@NoArgsConstructor
public class MessageConversation extends Auditable<String> {

    @Id
    private String id;

    private Set<User> participants;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;

    private MessageStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Training training;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
}
