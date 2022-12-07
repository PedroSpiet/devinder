package com.example.backcontainerusers.shared.adapters.inbound.controller;

import com.example.backcontainerusers.application.dto.SearchDTO;
import com.example.backcontainerusers.usecases.SearchUserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(value = SpringExtension.class)
@WebMvcTest(controllers = SearchUserController.class)
class SearchUserControllerUnit {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SearchUserUseCase searchUserUseCase;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("should be able to return 2xx")
    void testSearchUserController() throws Exception {
        Double latitude = -27.4568;
        Double longitude = -46.236;
        int distance = 10;
        Long searcher = 1L;

        String url = "/v1/search/" + latitude + "/" + longitude + "/" + distance + "/" + searcher;
        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("should be able to return 4xx")
    void testSearchUserControllerErr() throws Exception {
        Double latitude = -27.4568;
        Double longitude = -46.236;
        int distance = 10;
        Long searcher = 1L;

        String url = "/v1/search/" + latitude + "/" + "/" + distance + "/" + searcher;
        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}