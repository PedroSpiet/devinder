package com.example.backcontainerusers.application.dto;

import com.example.backcontainerusers.application.domain.enums.Genders;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class UserSearchRequestDto {
    private double minLat;
    private double maxLat ;
    private double minLong;
    private double maxLong;
    private String country;
    private Genders prefer_gender;
    private int age;
    private int maxYear;
    private int minYear;
    private LocalDate minDateDob;
    private LocalDate maxDateDob;
}
