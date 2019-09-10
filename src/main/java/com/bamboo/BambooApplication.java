package com.bamboo;

import com.bamboo.system.dao.UserRepository;
import com.bamboo.system.domain.SelfUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/3 20:04
 * @since JDK1.8
 */
@SpringBootApplication
public class BambooApplication {

    @Bean
    InitializingBean saveUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return () -> {
            SelfUser user = new SelfUser();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);

            SelfUser user1 = new SelfUser();
            user1.setUserName("test");
            user1.setPassword(passwordEncoder.encode("test"));
            userRepository.save(user1);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BambooApplication.class, args);
    }
}
