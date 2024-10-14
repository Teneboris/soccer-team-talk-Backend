package com.soccer.soccerTeamTalk.models.conversation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="status")
@Data
public class MessageStatus {

    @Id
    private String id;

    private EMessageStatus status;
}
