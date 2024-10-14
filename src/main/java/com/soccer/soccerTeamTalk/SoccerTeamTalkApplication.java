package com.soccer.soccerTeamTalk;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SoccerTeamTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerTeamTalkApplication.class, args);
	}
/*	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return strings -> {
			userRepository.save(new User("1", "Calvin", "calvin@gmail.com"));
			userRepository.save(new User("2", "Bryson", "raid@gmail.com"));
		};
	}*/
}


