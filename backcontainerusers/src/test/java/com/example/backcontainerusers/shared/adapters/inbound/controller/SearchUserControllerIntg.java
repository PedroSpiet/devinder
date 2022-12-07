package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.example.backcontainerusers.application.dto.SearchDTO;
import com.example.backcontainerusers.application.dto.UserResponseDTO;
import com.example.backcontainerusers.shared.adapters.outbound.persistence.repository.MySqlUserRepository;
import com.example.backcontainerusers.usecases.SearchUserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class SearchUserControllerIntg {

    @SpyBean
    SearchUserUseCase searchUserUseCase;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    MySqlUserRepository mySqlUserRepository;

    @Test
    @DisplayName("should be able to return 2xx")
    void testSearchUserController() throws Exception {
        Double latitude = -27.4568;
        Double longitude = -46.236;
        int distance = 10;
        Long searcher = 1L;

        String url = "/v1/search/" + latitude + "/" + longitude + "/" + distance + "/" + searcher;

        var userResponse = UserResponseDTO.builder()
                .birthdayDate(LocalDate.of(2022, 04, 05))
                .nameUser("Pedro Emanoel")
                .uf("SP")
                .city("Itanhaém")
                .country("Brazil")
                .description("aaa")
                .zodiac(ZodiacEnum.AQUARIUS)
                .gender(Genders.MAN).build();

        var r =  ResponseEntity.ok(SearchDTO.builder()
                .users(List.of(userResponse))
                .incompatible(false)
                .stage(SearchDTO.SearchStage.NORMAL)
                .build());

        BDDMockito.given(searchUserUseCase.search(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyLong())).willReturn(r);

        var res = testRestTemplate.exchange(url, HttpMethod.GET, null, SearchDTO.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}