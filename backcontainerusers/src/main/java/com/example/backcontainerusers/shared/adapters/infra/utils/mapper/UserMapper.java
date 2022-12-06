package com.example.backcontainerusers.shared.adapters.infra.utils.mapper;

import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.dto.UserResponseDTO;
import com.example.backcontainerusers.shared.adapters.outbound.persistence.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userDtoToUserEntity(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setNameUser(userDTO.getNameUser());
        user.setId(null);
        user.setCity(userDTO.getCity());
        user.setUf(userDTO.getUf());
        user.setDescription(userDTO.getDescription());
        user.setGender(userDTO.getGender());
        user.setPreferGender(userDTO.getPreferedGender());
        user.setDisabled(false);
        user.setIntention(userDTO.getIntention());
        user.setBirthdayDate(userDTO.getBirthdayDate());
        user.setConfirmedAcc(false);
        user.setMinimumAge(userDTO.getMinimumAge());
        user.setMaxAge(userDTO.getMaxAge());
        user.setMaxDistance(userDTO.getMaxDistance());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setGender(userDTO.getGender());
        user.setLocationLatitude(userDTO.getLocationLatitude());
        user.setLocationLongitude(userDTO.getLocationLongitude());
        user.setCountry(userDTO.getCountry());
        user.setZodiac(userDTO.getZodiac());

        return user;
    }

    public com.example.backcontainerusers.application.domain.User userEntityToDomain(User user) {
        return new com.example
                .backcontainerusers.application.domain.User(user.getNameUser(), user.getBirthdayDate(), user.getCity(),
                user.getUf(), user.getDescription(), user.getMaxAge(), user.getMinimumAge(), user.getDisabled(), user.getZodiac(),
                user.getIntention(), user.getConfirmedAcc(), user.getPreferGender(), user.getEmail(), user.getPassword(), user.getGender(),
                user.getLocationLatitude(), user.getLocationLongitude(), user.getCountry());
    }

    public UserDTO userEntityToDto(User result) {
        UserDTO user = new UserDTO();
        user.setNameUser(result.getNameUser());
        user.setCity(result.getCity());
        user.setUf(result.getUf());
        user.setDescription(result.getDescription());
        user.setGender(result.getGender());
        user.setPreferedGender(result.getPreferGender());
        user.setDisabled(false);
        user.setIntention(result.getIntention());
        user.setBirthdayDate(result.getBirthdayDate());
        user.setConfirmedAcc(false);
        user.setMinimumAge(result.getMinimumAge());
        user.setMaxAge(result.getMaxAge());
        user.setMaxDistance(result.getMaxDistance());
        user.setEmail(result.getEmail());
        user.setPassword(result.getPassword());
        user.setGender(result.getGender());
        user.setLocationLatitude(result.getLocationLatitude());
        user.setLocationLongitude(result.getLocationLongitude());
        user.setZodiac(result.getZodiac());
        return user;
    }

    public UserResponseDTO userEntityToResponse(User result) {
        UserResponseDTO user = new UserResponseDTO();
        user.setNameUser(result.getNameUser());
        user.setCity(result.getCity());
        user.setUf(result.getUf());
        user.setDescription(result.getDescription());
        user.setGender(result.getGender());
        user.setIntention(result.getIntention());
        user.setBirthdayDate(result.getBirthdayDate());
        user.setConfirmedAcc(false);
        user.setGender(result.getGender());
        user.setLocationLatitude(result.getLocationLatitude());
        user.setLocationLongitude(result.getLocationLongitude());
        user.setCountry(result.getCountry());
        user.setZodiac(result.getZodiac());
        return user;
    }
}
