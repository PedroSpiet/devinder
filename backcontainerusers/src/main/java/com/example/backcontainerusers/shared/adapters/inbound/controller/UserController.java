package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.shared.adapters.inbound.exceptions.ApiException;
import com.example.backcontainerusers.shared.adapters.inbound.resources.UserResource;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import com.example.backcontainerusers.usecases.UserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController implements UserResource {
    private final UserUseCase userUseCase;

    public UserController(final UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws ApiException {
        try {
            userUseCase.handler(userDTO);
            return ResponseEntity.ok(userDTO.getEmail());
        } catch (GenericErrorClass ex) {
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }
}
