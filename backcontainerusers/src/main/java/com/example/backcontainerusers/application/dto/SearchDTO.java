package com.example.backcontainerusers.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchDTO {

    public enum SearchStage {
        NORMAL,
        COUNTRY,
        WORLD,
        IGNORE_1,
        IGNORE_2,
        IGNORE_ALL
    }

    private List<UserResponseDTO> users;
    private String message;
    private SearchStage stage;

    boolean global;
    boolean incompatible;

}
