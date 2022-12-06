package com.example.backcontainerusers.usecases;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.ports.MySqlUserRepositoryPort;
import com.example.backcontainerusers.application.ports.UserUseCasePort;
import com.example.backcontainerusers.shared.adapters.config.PasswordEncoderConfig;
import com.example.backcontainerusers.shared.adapters.inbound.exceptions.ApiException;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class UserUseCase implements UserUseCasePort {

    private final MySqlUserRepositoryPort repositoryPort;
    private final PasswordEncoder PasswordEncoder;

    public UserUseCase(MySqlUserRepositoryPort repositoryPort, PasswordEncoder PasswordEncoder) {
        this.repositoryPort = repositoryPort;
        this.PasswordEncoder = PasswordEncoder;
    }

    public void handler(UserDTO userDTO) throws ApiException {
        try {
            userDTO.setConfirmedAcc(false);
            userDTO.setIsUpdate(false);
            var existsEmail = verifyEmail(userDTO.getEmail());

            if(existsEmail) {
                throw new GenericErrorClass("Email já existe na base de dados");
            }
            userDTO.setPassword(passwordEncoder(userDTO.getPassword()));
            repositoryPort.save(userDTO);
        } catch (Exception e) {
            throw ApiException.badRequest(e.getMessage(), "Erro ao salvar usuário novo");
        }
    }

    private String passwordEncoder(String password) {
        return PasswordEncoder.encode(password);
    }

    private boolean verifyEmail(String email) {
        return repositoryPort.existsByEmail(email);
    }

}
