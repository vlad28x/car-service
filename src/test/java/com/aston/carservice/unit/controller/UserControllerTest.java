package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.UserController;
import com.aston.carservice.controller.handler.GlobalExceptionHandler;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static com.aston.carservice.unit.controller.Util.asJsonString;
import static com.aston.carservice.unit.controller.Util.verifyBody;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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

    private UserRequestDto USER_REQUEST;

    private MockMvc mockMvc;

    private static final Long USER_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new UserController(userService))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        USER_RESPONSE = new UserResponseDto(USER_ID);
        USER_RESPONSE.setUsername("username");
        USER_RESPONSE.setEmail("email@gmail.com");
        USER_RESPONSE.setSalary(100_000L);
        USER_RESPONSE.setRole(new RoleResponseDto(1L));
        USER_RESPONSE.setCarServiceId(1L);

        USER_REQUEST = new UserRequestDto();
        USER_REQUEST.setUsername("username");
        USER_REQUEST.setEmail("email@gmail.com");
        USER_REQUEST.setPassword("hardpassword");
        USER_REQUEST.setSalary(100_000L);
        USER_REQUEST.setRoleId(1L);
        USER_REQUEST.setCarServiceId(1L);
    }

    @Test
    void getByIdMethod_shouldReturnUser() throws Exception {
        when(userService.getById(USER_ID)).thenReturn(USER_RESPONSE);

        MvcResult mvcResult = mockMvc
                    .perform(get("/api/v1/users/1").param("id", String.valueOf(USER_ID))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(USER_RESPONSE)))
                    .andExpect(status().isOk())
                    .andReturn();
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getByIdMethod_ifUserNotFound_thenReturnNotFoundException() throws Exception {
        when(userService.getById(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMethod_shouldReturnAllUsers() throws Exception {
            when(userService.getAll()).thenReturn(Collections.singletonList(USER_RESPONSE));

            MvcResult mvcResult = mockMvc
                    .perform(get("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(USER_RESPONSE)))
                    .andExpect(status().isOk())
                    .andReturn();
            verifyBody(asJsonString(Collections.singletonList(USER_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateUser() throws Exception {
        doReturn(USER_RESPONSE).when(userService).create(any(UserRequestDto.class));

        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/users")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_REQUEST)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(USER_REQUEST), mvcResult.getRequest().getContentAsString());
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateUser() throws Exception {
        doReturn(USER_RESPONSE).when(userService).update(any(Long.class), any(UserRequestDto.class));

        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_REQUEST)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(USER_REQUEST), mvcResult.getRequest().getContentAsString());
        verifyBody(asJsonString(USER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateMethod_ifUserNotFound_thenReturnNotFoundException() throws Exception {
        when(userService.update(any(Long.class), any(UserRequestDto.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/api/v1/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_REQUEST)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMethod_shouldDeleteUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_ifUserNotFound_thenReturnNotFoundException() throws Exception {
        when(userService.delete(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(delete("/api/v1/users/1")
                        .param("id", String.valueOf(USER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(USER_RESPONSE)))
                .andExpect(status().isNotFound());
    }

}