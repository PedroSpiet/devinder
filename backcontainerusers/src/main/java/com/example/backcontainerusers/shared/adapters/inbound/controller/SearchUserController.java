package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.dto.SearchDTO;
import com.example.backcontainerusers.application.ports.SearchUserUseCasePort;
import com.example.backcontainerusers.shared.adapters.inbound.resources.SearchUserResource;
import com.example.backcontainerusers.shared.adapters.outbound.exceptions.GenericErrorClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchUserController implements SearchUserResource {

    private final SearchUserUseCasePort searchUserUseCasePort;

    public SearchUserController(SearchUserUseCasePort searchUserUseCasePort) {
        this.searchUserUseCasePort = searchUserUseCasePort;
    }

    @Override
    public ResponseEntity<?> searchUsers(Double latitude, Double longitude, int distance, Long search) {
       try {
           return searchUserUseCasePort.search(latitude, longitude, distance, search);
       } catch (GenericErrorClass ex) {
           return ResponseEntity.badRequest()
                   .body(ex.getMessage());
       }
    }

    @Override
    public ResponseEntity<?> searchUsersWithoutLocation(Long searcher) {
        try {
            return searchUserUseCasePort.searchWithoutLocation(searcher);
        } catch (GenericErrorClass ex) {
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }
}
