package com.example.backcontainerusers.application.ports;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.shared.adapters.inbound.exceptions.ApiException;

public interface UserUseCasePort {
    void handler(UserDTO userDTO) throws ApiException;
}
