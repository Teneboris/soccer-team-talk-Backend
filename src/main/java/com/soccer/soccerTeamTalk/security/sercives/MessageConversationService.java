package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.models.conversation.Message;
import com.soccer.soccerTeamTalk.models.conversation.MessageConversation;

public interface MessageConversationService {

     MessageConversation createNewConversation(MessageConversation message);

     MessageConversation sendingMessage(MessageConversation message);

     MessageConversation receivingMessage();

     MessageConversation retrievingConversationHistory();

     MessageConversation SearchingForConversations();

}
