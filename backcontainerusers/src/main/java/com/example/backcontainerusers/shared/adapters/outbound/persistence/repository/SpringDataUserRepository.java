package com.example.backcontainerusers.shared.adapters.outbound.persistence.repository;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import com.example.backcontainerusers.shared.adapters.outbound.persistence.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    default List<User> searchUser(UserSearchRequestDto user, Pageable page) {
        return searchQuery(user.getMinLat(),
                user.getMaxLat(), user.getMinLong(), user.getMaxLong(), user.getPrefer_gender(), page);
    }

    @Query(value = """
                 SELECT u FROM User u WHERE u.gender = :prefer_gender AND u.disabled = FALSE 
                 AND u.locationLatitude IS NOT NULL and u.locationLongitude IS NOT NULL
                 AND u.locationLatitude BETWEEN :latitudeFrom AND :latitudeTo AND u.locationLongitude BETWEEN :longitudeFrom AND :longitudeTo
            """)
    List<User> searchQuery(
            @Param("latitudeFrom") Double latitudeFrom, @Param("latitudeTo") Double latitudeTo,
            @Param("longitudeFrom") Double longitudeFrom, @Param("longitudeTo") Double longitudeTo,
            @Param("prefer_gender") Genders prefer_gender, Pageable page);

    @Query(value = """
                SELECT u FROM User u WHERE u.gender = :prefer_gender AND u.disabled = FALSE 
                AND u.country = :country
            """)
    List<User> findAllByCountry(String country, Pageable pageable);
}
