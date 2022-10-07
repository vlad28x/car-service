package com.aston.carservice.unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class Util {
    static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    static void verifyBody(String expectedBody, String actualBody) {
        assertThat(actualBody).isEqualTo(expectedBody);
    }
}
