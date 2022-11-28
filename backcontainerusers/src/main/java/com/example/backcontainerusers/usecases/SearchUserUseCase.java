package com.example.backcontainerusers.usecases;

import com.example.backcontainerusers.application.dto.SearchDTO;
import com.example.backcontainerusers.application.dto.UserSearchRequestDto;
import com.example.backcontainerusers.application.ports.MySqlUserRepositoryPort;
import com.example.backcontainerusers.application.ports.SearchUserUseCasePort;


import com.example.backcontainerusers.shared.adapters.infra.utils.tools.DateTools;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;


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

        // https://stackoverflow.com/questions/23117989/get-the-max-latitude-and-longitude-given-radius-meters-and-position/23118314#23118314
        double deltaLat = distance / LATITUDE;
        double deltaLong = distance / (LONGITUDE * Math.cos(latitude / 180.0 * Math.PI));
        double minLat = latitude - deltaLat;
        double maxLat = latitude + deltaLat;
        double minLong = longitude - deltaLong;
        double maxLong = longitude + deltaLong;

        var age = searcher.getBirthdayDate().getYear();
        var minAge = searcher.getMinimumAge().getYear();
        var maxAge = searcher.getMaxAge().getYear();

        var searchRequest = UserSearchRequestDto.builder()
                .minDateDob(searcher.getMinimumAge())
                .maxLong(maxLong)
                .age(age)
                .maxYear(maxAge)
                .minYear(minAge)
                .maxLat(maxLat)
                .prefer_gender(searcher.getPreferedGender())
                .maxDateDob(searcher.getMaxAge())
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


    public ResponseEntity<SearchDTO> searchWithoutLocation(Long searcherId) {

        var searcher = repositoryPort.findById(searcherId);
        var minAge = searcher.getMinimumAge().getYear();
        var maxAge = searcher.getMaxAge().getYear();

        var age = searcher.getBirthdayDate().getYear();
        var searchRequest = UserSearchRequestDto.builder()
                .minDateDob(searcher.getMinimumAge())
                .prefer_gender(searcher.getPreferedGender())
                .maxDateDob(searcher.getMaxAge())
                .age(age)
                .maxYear(maxAge)
                .minYear(minAge)
                .country(searcher.getCountry())
                .build();

        var users = repositoryPort.findByCountry(searchRequest, PageRequest.of(0, SEARCH_MAX));

        return ResponseEntity.ok(SearchDTO.builder()
                .users(users)
                .incompatible(false)
                .stage(SearchDTO.SearchStage.COUNTRY)
                .build());
    }
}
