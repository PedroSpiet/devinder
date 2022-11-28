package com.example.backcontainerusers.shared.adapters.outbound.exceptions;

import com.example.backcontainerusers.shared.adapters.outbound.exceptions.dto.ExceptionErrorDTO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@ToString
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionErrorDTO errorDTO = ExceptionErrorDTO.builder()
                .message(formattedString(ex))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        log.error(errorDTO.toString());
        return ResponseEntity
                .badRequest()
                .body(errorDTO);
    }

    private String formattedString(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return errors.stream().map(
                fieldError -> fieldError.getField() + "= " + fieldError.getDefaultMessage()
        ).sorted().collect(Collectors.joining(","));
    }
}
