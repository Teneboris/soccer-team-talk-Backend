package com.soccer.soccerTeamTalk.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soccer.soccerTeamTalk.audit.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "games")
public class Game extends Auditable<String> {

    @Id
    @Setter
    @Getter
    private String id;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    private String opponent;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    private int telephone;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    private String address;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    private String city;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    @Min(value=5, message="postalCode must be equal to 5")
    @Max(value=5, message="postalCode must be equal to 5")
    private int postalCode;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    private String comment;

    @NotNull(message = "{validation.age.NotNull}")
    @Setter
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public Game(String opponent, int telephone, String address, String city, int postalCode, String comment, Date date) {
        this.opponent = opponent;
        this.telephone = telephone;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.comment = comment;
        this.date = date;
    }
}
