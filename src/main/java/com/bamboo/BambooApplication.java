package com.bamboo;

import com.bamboo.system.repository.UserRepository;
import com.bamboo.system.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:04
 * @since JDK1.8
 */
@SpringBootApplication
public class BambooApplication {

    @Bean
    InitializingBean saveUser(UserRepository userRepository) {
        return () -> {
            User user = new User();
            user.setUserName("admin");
            user.setPassword("admin");
            userRepository.save(user);

            User user1 = new User();
            user1.setUserName("test");
            user1.setPassword("test");
            userRepository.save(user1);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BambooApplication.class, args);
    }
}
