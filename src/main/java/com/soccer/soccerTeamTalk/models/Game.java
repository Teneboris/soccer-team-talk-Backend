package com.soccer.soccerTeamTalk.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soccer.soccerTeamTalk.audit.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "games")
@Data
@NoArgsConstructor
public class Game extends Auditable<String> {

    @Id
    private String id;

    @NotNull(message = "{validation.age.NotNull}")
    private String opponent;

    @NotNull(message = "{validation.age.NotNull}")
    private Long telephone;

    @NotNull(message = "{validation.age.NotNull}")
    private String address;

    @NotNull(message = "{validation.age.NotNull}")
    private String city;

    @NotNull(message = "{validation.age.NotNull}")
    @Min(value=5, message="postalCode must be equal to 5")
    @Max(value=5, message="postalCode must be equal to 5")
    private int postalCode;

    @NotNull(message = "{validation.age.NotNull}")
    private String comment;

    @NotNull(message = "{validation.age.NotNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

}
