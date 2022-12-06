package com.example.backcontainerusers.shared.adapters.outbound.persistence.repository;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import com.example.backcontainerusers.shared.adapters.outbound.persistence.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    default List<User> searchUser(UserSearchRequestDto user, Pageable page) {
        return searchQuery(user.getMinLat(),
                user.getMaxLat(), user.getMinLong(), user.getMaxLong(), user.getPrefer_gender(), user.getMinYear(), user.getMaxYear(), page);
    }

    @Query(value = """
             SELECT u FROM User u WHERE u.gender = :prefer_gender AND u.disabled = FALSE 
             AND u.locationLatitude IS NOT NULL and u.locationLongitude IS NOT NULL
             AND u.locationLatitude BETWEEN :latitudeFrom AND :latitudeTo AND u.locationLongitude BETWEEN :longitudeFrom AND :longitudeTo
            AND TIMESTAMPDIFF(YEAR, u.birthdayDate, NOW()) >= :min_year AND TIMESTAMPDIFF(YEAR, u.birthdayDate, NOW()) <= :max_year
                                                          """)
    List<User> searchQuery(
            @Param("latitudeFrom") Double latitudeFrom, @Param("latitudeTo") Double latitudeTo,
            @Param("longitudeFrom") Double longitudeFrom, @Param("longitudeTo") Double longitudeTo,
            @Param("prefer_gender") Genders prefer_gender,
            @Param("min_year") int minYear, @Param("max_year") int maxYear, Pageable page);

    @Query(value = """
                SELECT u FROM User u WHERE u.gender = :prefer_gender AND u.disabled = FALSE 
                AND u.country = :country
                AND u.birthdayDate >= :min_date_dob 
                AND u.birthdayDate <= :max_date_dob

            """)
    List<User> findAllByCountry(String country, @Param("min_date_dob") LocalDate minDateDob, @Param("max_date_dob") LocalDate maxDateDob, Pageable pageable);
}
