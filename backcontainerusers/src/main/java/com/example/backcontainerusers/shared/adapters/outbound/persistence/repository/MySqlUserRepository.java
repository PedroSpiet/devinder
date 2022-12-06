package com.example.backcontainerusers.shared.adapters.outbound.persistence.repository;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.dto.UserResponseDTO;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import com.example.backcontainerusers.application.ports.MySqlUserRepositoryPort;
import com.example.backcontainerusers.shared.adapters.infra.utils.mapper.UserMapper;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MySqlUserRepository implements MySqlUserRepositoryPort {
    private final UserMapper userMapper;
    private final SpringDataUserRepository springDataUserRepository;

    public MySqlUserRepository(final UserMapper userMapper, SpringDataUserRepository repository) {
        this.userMapper = userMapper;
        this.springDataUserRepository = repository;
    }

    public void save(UserDTO userDTO) {
        var converted = userMapper.userDtoToUserEntity(userDTO);

        if (userDTO.getIsUpdate()) {
            var user = springDataUserRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new GenericErrorClass("Not FOund!"));

            user.setLocationLatitude(userDTO.getLocationLatitude());
            user.setLocationLongitude(userDTO.getLocationLongitude());

            var saved = springDataUserRepository.saveAndFlush(user);
            userMapper.userEntityToDomain(saved);
            return;
        }

        var result = springDataUserRepository.save(converted);
        userMapper.userEntityToDomain(result);
    }

    @Override
    public List<UserDTO> findByCountry(UserSearchRequestDto searchRequestDto, Pageable pageable) {
        var users = springDataUserRepository.findAllByCountry(searchRequestDto.getCountry(), searchRequestDto.getMinDateDob(), searchRequestDto.getMaxDateDob(), pageable);
        return users.stream().map(userMapper::userEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        var result = springDataUserRepository.findById(id).orElseThrow(() -> {
            throw new GenericErrorClass("User not found");
        });


        return userMapper.userEntityToDto(result);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email);
    }

    @Override
    public List<UserResponseDTO> searchUsers(UserSearchRequestDto searchRequestDto, Pageable page) {
        var result =  springDataUserRepository.searchUser(searchRequestDto, page);

        return result.stream().map(userMapper::userEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO saveAndFlush(UserDTO userDTO) {
        var mapper = userMapper.userDtoToUserEntity(userDTO);
        var result = springDataUserRepository.saveAndFlush(mapper);

        return userMapper.userEntityToDto(result);
    }
}
