package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.application.ports.UserUseCasePort;
import com.example.backcontainerusers.shared.adapters.inbound.exceptions.ApiException;
import com.example.backcontainerusers.usecases.UserUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class UserControllerTestIntg {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TestRestTemplate testRestTemplate;

    @SpyBean
    UserUseCasePort userServicePort;

    @Test
    @DisplayName("should be able save a user")
    void testUserRegister() throws JsonProcessingException, ApiException {
        var body = UserDTO.builder()
                .nameUser("PedroSpiet")
                .birthdayDate(LocalDate.of(2022, 05, 04))
                .city("Itanhaem")
                .uf("SP")
                .email("pedrospiet@gmail.com")
                .password("12345")
                .description("blabla")
                .maxAge(28)
                .minimumAge(18)
                .gender(Genders.WOMAN)
                .preferedGender(Genders.MAN)
                .zodiac(ZodiacEnum.AQUARIUS)
                .intention(UserIntention.CONVERSATION)
                .country("Brazil")
                .locationLatitude(-27.4568)
                .locationLongitude(-46.236)
                .isUpdate(false)
                .confirmedAcc(false)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        var obj = objectMapper.writeValueAsString(body);

        HttpEntity<String> entity = new HttpEntity<>(obj, httpHeaders);

        var res = testRestTemplate
                .exchange("/v1/users", HttpMethod.POST, entity, String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}