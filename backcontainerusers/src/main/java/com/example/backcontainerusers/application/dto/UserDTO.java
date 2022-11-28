package com.example.backcontainerusers.application.dto;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotNull
    @JsonProperty(value = "name_user")
    String nameUser;

    @NotNull
    @JsonProperty(value = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthdayDate;

    @NotNull
    String city;

    @NotNull
    String uf;

    @NotNull
    String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty(value = "maximum_age")
    LocalDate maxAge;

    @NotNull
    @JsonProperty(value = "minimum_age")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate minimumAge;

    @NotNull
    @JsonProperty(value = "country")
    private String country;
    
    boolean disabled;

    ZodiacEnum zodiac;

    UserIntention intention;

    @JsonProperty(value = "confirmed_acc")
    boolean confirmedAcc;

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

    @JsonIgnore
    private Double locationLatitude;

    @JsonIgnore
    private Double locationLongitude;
}
