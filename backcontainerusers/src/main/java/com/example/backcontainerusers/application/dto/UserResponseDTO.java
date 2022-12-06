package com.example.backcontainerusers.application.dto;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    @JsonProperty(value = "name_user")
    String nameUser;

    @JsonProperty(value = "birthday_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate birthdayDate;

    String city;

    String uf;

    String description;

    @JsonProperty(value = "country")
    private String country;

    ZodiacEnum zodiac;

    UserIntention intention;

    @JsonProperty(value = "confirmed_acc")
    boolean confirmedAcc;

    Genders gender;

    @JsonProperty(value = "latitude")
    private Double locationLatitude;

    @JsonProperty(value = "longitude")
    private Double locationLongitude;
}
