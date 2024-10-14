package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.ERole;
import com.soccer.soccerTeamTalk.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
    Optional<Role> findByName(ERole name);
}
