package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.UserController;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @MockBean
    private UserService userService;

    private UserResponseDto USER_RESPONSE;

    private MockMvc mockMvc;

    private static final Long USER_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new UserController(userService)).build();

        USER_RESPONSE = new UserResponseDto();
        USER_RESPONSE.setId(USER_ID);
        USER_RESPONSE.setUsername("username");
        USER_RESPONSE.setEmail("email@gmail.com");
        USER_RESPONSE.setSalary(100_000L);
        USER_RESPONSE.setRole(new RoleResponseDto(1L));
        USER_RESPONSE.setCarServiceId(1L);
    }

    @Test
    void getByIdMethod_shouldReturnUser() throws Exception {
        when(userService.getById(USER_ID)).thenReturn(USER_RESPONSE);

        MvcResult mvcResult = mockMvc
                    .perform(get("/users/1").param("id", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(USER_RESPONSE)))
                    .andExpect(status().isOk())
                    .andReturn();
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllMethod_shouldReturnAllUsers() throws Exception {
            when(userService.getAll()).thenReturn(Collections.singletonList(USER_RESPONSE));
            MvcResult mvcResult = mockMvc
                    .perform(get("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(USER_RESPONSE)))
                    .andExpect(status().isOk())
                    .andReturn();
            verifyBody(asJsonString(Collections.singletonList(USER_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/users")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_shouldDeleteUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    private void verifyBody(String expectedBody, String actualBody) {
        assertThat(actualBody).isEqualTo(expectedBody);
    }

}