package com.example.backcontainerusers.application.dto;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    @NotNull
    @JsonProperty(value = "name_user")
    String nameUser;

    private Boolean isUpdate;

    @NotNull
    @JsonProperty(value = "birthday_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate birthdayDate;

    @NotNull
    String city;

    @NotNull
    String uf;

    @NotNull
    String description;

    @NotNull
    @JsonProperty(value = "maximum_age")
    int maxAge;

    @NotNull
    @JsonProperty(value = "minimum_age")
    int minimumAge;

    @NotNull
    @JsonProperty(value = "country")
    private String country;

    Boolean disabled;

    ZodiacEnum zodiac;

    UserIntention intention;

    @JsonProperty(value = "confirmed_acc")
    Boolean confirmedAcc;

    @NotNull
    @JsonProperty(value = "prefered_gender")
    Genders preferedGender;

    @NotNull
    Genders gender;

    @NotNull
    @JsonProperty(value = "max_distance")
    int maxDistance;

    @NotNull @Email
    String email;

    @NotNull
    String password;

    @JsonProperty(value = "latitude")
    private Double locationLatitude;

    @JsonProperty(value = "longitude")
    private Double locationLongitude;
}
