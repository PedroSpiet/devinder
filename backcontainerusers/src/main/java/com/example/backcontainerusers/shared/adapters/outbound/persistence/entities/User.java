package com.example.backcontainerusers.shared.adapters.outbound.persistence.entities;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_user", nullable = false)
    private String nameUser;

    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthdayDate;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "max_age", nullable = false)
    int maxAge;

    @Column(name = "minimum_age", nullable = false)
    int minimumAge;

    @Column(name = "zodiac")
    @Enumerated(EnumType.STRING)
    ZodiacEnum zodiac;

    @Column(name = "intention")
    @Enumerated(EnumType.STRING)
    UserIntention intention;

    @Column(name = "confirmed_acc")
    Boolean confirmedAcc;

    Boolean disabled;

    @Column(name = "prefer_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    Genders preferGender;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    Genders gender;

    @Column(name = "max_distance")
    int maxDistance;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    private Double locationLatitude;

    private Double locationLongitude;
}
