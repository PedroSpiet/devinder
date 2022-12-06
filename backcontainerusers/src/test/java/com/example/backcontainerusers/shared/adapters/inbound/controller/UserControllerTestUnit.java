package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.domain.enums.Genders;
import com.example.backcontainerusers.application.domain.enums.UserIntention;
import com.example.backcontainerusers.application.domain.enums.ZodiacEnum;
import com.example.backcontainerusers.application.dto.UserDTO;
import com.example.backcontainerusers.usecases.UserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AutoConfigureMockMvc
@ExtendWith(value = SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTestUnit {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserUseCase userUseCase;

    @Test
    @DisplayName("POST /v1/users should return 2xx")
    void testCreateUser_2xx() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyy");

        var body = UserDTO.builder()
                .nameUser("PedroSpiet")
                .birthdayDate(LocalDate.parse("24/05/2002", df))
                .city("Itanhaem")
                .uf("SP")
                .email("Pedrospiet@gmail.com")
                .password("12345")
                .confirmedAcc(false)
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
                .build();

        var dto = objectMapper.writeValueAsString(body);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(dto)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void testCreateUser_4xx() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyy");


        var body = UserDTO.builder()
                .nameUser("PedroSpiet")
                .birthdayDate(LocalDate.parse("24/05/2002", df))
                .city("Itanhaem")
                .uf("SP")
                .email(null)
                .password("12345")
                .confirmedAcc(false)
                .description("blabla")
                .maxAge(25)
                .minimumAge(18)
                .gender(Genders.WOMAN)
                .preferedGender(Genders.MAN)
                .zodiac(ZodiacEnum.AQUARIUS)
                .intention(UserIntention.CONVERSATION).build();

        var dto = objectMapper.writeValueAsString(body);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(dto)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
}