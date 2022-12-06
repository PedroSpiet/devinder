package com.example.backcontainerusers.application.domain;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public record User(@NotNull @NotBlank @Min(value = 5) String nameUser,
                   @NotNull @NotBlank LocalDate birthdayDate, @NotNull @NotBlank String city,
                   @NotNull @NotBlank String uf,
                   @NotNull @NotBlank String description, @NotNull @NotBlank int maxAge,
                   @NotNull @NotBlank int minimumAge, Boolean disabled, ZodiacEnum zodiac,
                   UserIntention intention, Boolean confirmedAcc, @NotBlank @NotNull Genders preferedGender,
                   @NotNull @Email String email, @NotNull String password, @NotNull Genders gender,
                   Double latitude, Double longitude, String country) {

}
