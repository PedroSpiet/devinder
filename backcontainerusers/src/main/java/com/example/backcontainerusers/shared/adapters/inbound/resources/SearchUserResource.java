package com.example.backcontainerusers.shared.adapters.inbound.resources;

import com.example.backcontainerusers.application.dto.SearchDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/v1/search")
@Api(value = "/v1/search", tags = "Search Controller")
public interface SearchUserResource {

    @GetMapping(value = "/{latitude}/{longitude}/{distance}/{search}")
    @ApiOperation(value = "Information About users by params", response = SearchDTO.class)
    public ResponseEntity<?> searchUsers(@PathVariable Double latitude, @PathVariable Double longitude,
                                         @PathVariable int distance, @PathVariable Long search);


}
