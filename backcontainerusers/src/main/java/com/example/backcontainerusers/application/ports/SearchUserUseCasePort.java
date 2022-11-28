package com.example.backcontainerusers.application.ports;

import com.example.backcontainerusers.application.dto.SearchDTO;
import org.springframework.http.ResponseEntity;


public interface SearchUserUseCasePort {
    ResponseEntity<SearchDTO> search(Double latitude, Double longitude, int distance,
                                     Long searcher);

    ResponseEntity<SearchDTO> searchWithoutLocation(Long searcherId);

}
