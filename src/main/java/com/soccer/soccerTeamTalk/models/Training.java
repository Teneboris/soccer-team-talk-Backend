package com.soccer.soccerTeamTalk.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soccer.soccerTeamTalk.audit.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Date;

@Document(collection = "trainings")
@Setter
@Getter
public class Training extends Auditable<String> {

    @Id
    private String id;

    @NotEmpty(message = "{validation.name.NotEmpty}")
    private String address;

    @NotEmpty(message = "{validation.name.NotEmpty}")
    private String city;

    @NotNull(message = "{validation.age.NotNull}")
    @Min(value=5, message="postalCode must be equal to 5")
    @Max(value=5, message="postalCode must be equal to 5")
    private int postalCode;

    @NotEmpty(message = "{validation.name.NotEmpty}")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotEmpty(message = "{validation.name.NotEmpty}")
    private Date date;

    public Training(String address, String city, int postalCode, String description, Date date) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.description = description;
        this.date = date;
    }
}
