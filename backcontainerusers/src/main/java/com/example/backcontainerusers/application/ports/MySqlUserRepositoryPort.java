package com.example.backcontainerusers.application.ports;

import com.example.backcontainerusers.application.domain.User;
import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MySqlUserRepositoryPort {
    User save(UserDTO userDTO);
    List<UserDTO> findByCountry(UserSearchRequestDto searchRequestDto, Pageable pageable);
    UserDTO findById(Long id);
    Boolean existsByEmail(String email);
    List<UserDTO> searchUsers(UserSearchRequestDto searchRequestDto, Pageable page);
    //UserDTO saveAndFlush(UserDTO userDTO);
}
