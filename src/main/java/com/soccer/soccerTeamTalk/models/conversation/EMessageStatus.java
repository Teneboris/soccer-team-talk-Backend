package com.soccer.soccerTeamTalk.models.conversation;

public enum EMessageStatus {

    READ {
        public String getDescription() {
            return "Message was read";
        }
    },

    DELETED,

    ACCEPTED,

    FAILED {
        public String getDescription() {
            return "Message was failed";
        }
    },

    SENT,

    PENDING {
        public String getDescription() {
            return "Message was pending";
        }
    },
}
