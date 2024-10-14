package com.soccer.soccerTeamTalk.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public abstract class Auditable<U> {

    @CreatedBy
    @Field("created_by")
    private U createdBy;

    @CreatedDate
    @Field("created_date")
    private Instant createdDate;

    @LastModifiedBy
    @Field("last_modified_by")
    private U lastModifiedBy;

    @LastModifiedDate
    @Field("last_modified_date")
    private Instant lastModifiedDate;

}

