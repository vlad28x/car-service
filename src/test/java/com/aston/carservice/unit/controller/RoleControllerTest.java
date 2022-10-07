package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.RoleController;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.service.RoleService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(RoleController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleControllerTest {

    @MockBean
    private RoleService roleService;

    private RoleResponseDto ROLE_RESPONSE;

    private MockMvc mockMvc;

    private static final Long ROLE_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new RoleController(roleService)).build();

        ROLE_RESPONSE = new RoleResponseDto(ROLE_ID);
        ROLE_RESPONSE.setName("ADMIN");
    }

    @Test
    void getByIdMethod_shouldReturnRole() throws Exception {
        when(roleService.getById(ROLE_ID)).thenReturn(ROLE_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/roles/1")
                        .param("id", String.valueOf(ROLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ROLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ROLE_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllMethod_shouldReturnAllRoles() throws Exception {
        when(roleService.getAll()).thenReturn(Collections.singletonList(ROLE_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/roles")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ROLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(ROLE_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateRole() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/roles")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ROLE_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(ROLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateRole() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/roles/1")
                        .param("id", String.valueOf(ROLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ROLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ROLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_shouldDeleteRole() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/roles/1")
                        .param("id", String.valueOf(ROLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

}
