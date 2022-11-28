package com.example.backcontainerusers.shared.adapters.config;

import com.example.backcontainerusers.BackcontainerusersApplication;
import com.example.backcontainerusers.application.ports.MySqlUserRepositoryPort;
import com.example.backcontainerusers.application.ports.SearchUserUseCasePort;
import com.example.backcontainerusers.usecases.SearchUserUseCase;
import com.example.backcontainerusers.usecases.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackageClasses = BackcontainerusersApplication.class)
public class BeanController {

    @Bean
    public UserUseCase userUseCase(MySqlUserRepositoryPort mySqlUserRepositoryPort, PasswordEncoder
            passwordEncoderConfig) {
        return new UserUseCase(mySqlUserRepositoryPort, passwordEncoderConfig);
    }

    @Bean
    public SearchUserUseCase searchUserUseCase(MySqlUserRepositoryPort mySqlUserRepositoryPort) {
        return new SearchUserUseCase(mySqlUserRepositoryPort);
    }
}
