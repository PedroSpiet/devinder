package com.example.backcontainerusers.usecases;

import com.example.backcontainerusers.application.dto.SearchDTO;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import com.example.backcontainerusers.application.ports.MySqlUserRepositoryPort;
import com.example.backcontainerusers.application.ports.SearchUserUseCasePort;


import com.example.backcontainerusers.shared.adapters.infra.utils.tools.DateTools;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;


public class SearchUserUseCase implements SearchUserUseCasePort {
    private static final double LATITUDE = 111.1;
    private static final double LONGITUDE = 111.320;

    private static final int SEARCH_MAX = 200;
    private static final int MAX_DISATANCE = 285;

    private final MySqlUserRepositoryPort repositoryPort;

    public SearchUserUseCase(MySqlUserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public ResponseEntity<SearchDTO> search(Double latitude, Double longitude, int distance,
                                            Long searcherId) {

        var searcher = repositoryPort.findById(searcherId);

        if (distance > MAX_DISATANCE) {
            throw new GenericErrorClass("Distância máxima excedida");
        }

        if (searcher.getLocationLatitude() == null && searcher.getLocationLongitude() == null) {
            throw new GenericErrorClass("Não foram encontrados dados de geolocalização");
        }

        if (searcher.getLocationLatitude() != latitude ||  searcher.getLocationLongitude() != longitude) {
            searcher.setLocationLatitude(latitude);
            searcher.setLocationLongitude(longitude);
            searcher.setId(searcherId);
            searcher.setIsUpdate(true);
            repositoryPort.save(searcher);
        }

        // https://stackoverflow.com/questions/23117989/get-the-max-latitude-and-longitude-given-radius-meters-and-position/23118314#23118314
        double deltaLat = distance / LATITUDE;
        double deltaLong = distance / (LONGITUDE * Math.cos(latitude / 180.0 * Math.PI));
        double minLat = latitude - deltaLat;
        double maxLat = latitude + deltaLat;
        double minLong = longitude - deltaLong;
        double maxLong = longitude + deltaLong;

        var age =  LocalDate.now().getYear() - searcher.getBirthdayDate().getYear();
        var minAge = searcher.getMinimumAge();
        var maxAge = searcher.getMaxAge();

        var minDate = DateTools.ageToDate(age);
        var minLocalDate = DateTools.dateToLocalDate(minDate);
        var maxDate = DateTools.ageToDate(maxAge);
        var maxLocalDate = DateTools.dateToLocalDate(maxDate);

        var searchRequest = UserSearchRequestDto.builder()
                .minDateDob(minLocalDate)
                .maxLong(maxLong)
                .age(age)
                .maxYear(maxAge)
                .minYear(minAge)
                .maxLat(maxLat)
                .prefer_gender(searcher.getPreferedGender())
                .maxDateDob(maxLocalDate)
                .minLat(minLat)
                .minLong(minLong)
                .build();

        var users = repositoryPort.searchUsers(searchRequest, PageRequest.of(0, SEARCH_MAX));

        return ResponseEntity.ok(SearchDTO.builder()
                .users(users)
                .incompatible(false)
                .stage(SearchDTO.SearchStage.NORMAL)
                .build());
    }

}
