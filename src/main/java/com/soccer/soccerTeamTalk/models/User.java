package com.soccer.soccerTeamTalk.models;

import com.soccer.soccerTeamTalk.audit.Auditable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@NoArgsConstructor
public class User extends Auditable<String> {

    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    @Email(message = "{validation.email.Type}")
    private String email;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username,String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
