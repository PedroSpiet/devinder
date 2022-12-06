package com.example.backcontainerusers.application.ports;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.dto.UserResponseDTO;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MySqlUserRepositoryPort {
    void save(UserDTO userDTO);
    List<UserDTO> findByCountry(UserSearchRequestDto searchRequestDto, Pageable pageable);
    UserDTO findById(Long id);
    Boolean existsByEmail(String email);
    List<UserResponseDTO> searchUsers(UserSearchRequestDto searchRequestDto, Pageable page);
    UserDTO saveAndFlush(UserDTO userDTO);
}
