package com.example.backcontainerusers.shared.adapters.outbound.exceptions.dto;

import lombok.Builder;

@Builder
public class ExceptionErrorDTO {
    private int statusCode;
    private String message;

}
