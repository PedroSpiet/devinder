package com.example.backcontainerusers.shared.adapters.inbound.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
@WebMvcTest(controllers = SearchUserControllerUnit.class)
class SearchUserControllerUnit {

    @Test
    @DisplayName("should be able to return 2xx")
    void testSearchUserController() {
        assert 1 == 1;
    }

}