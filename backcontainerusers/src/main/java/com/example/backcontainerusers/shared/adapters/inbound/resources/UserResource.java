package com.example.backcontainerusers.shared.adapters.inbound.resources;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.shared.adapters.inbound.exceptions.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/v1/users")
@Api(value = "/v1/users", tags = "Users")
public interface UserResource {

    @PostMapping()
    @ApiOperation(value = "Create user by param", response = String.class)
    public ResponseEntity<String> createUser(@Valid UserDTO userDTO) throws ApiException;
}
