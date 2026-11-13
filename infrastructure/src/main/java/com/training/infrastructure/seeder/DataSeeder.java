package com.training.infrastructure.seeder;

import com.training.infrastructure.UserJpaRepository;
import com.training.infrastructure.persistence.Adress.AddressJpaEntity;
import com.training.infrastructure.persistence.User.UserJpaEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    CommandLineRunner seedData(UserJpaRepository userRepository) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            List<UserJpaEntity> users = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                UserJpaEntity user =
                        new UserJpaEntity(null, "User" + i + "@yopmail.com","User " + i);

                user.addAddress(
                        new AddressJpaEntity("Address " + i + " - 1")
                );

                user.addAddress(
                        new AddressJpaEntity("Address " + i + " - 2")
                );

                users.add(user);
            }

            userRepository.saveAll(users);
        };
    }
}
