package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.ServiceController;
import com.aston.carservice.controller.handler.GlobalExceptionHandler;
import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.ServiceService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(ServiceController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceControllerTest {

    @MockBean
    private ServiceService serviceService;

    private ServiceResponseDto SERVICE_RESPONSE;

    private MockMvc mockMvc;

    private static final Long SERVICE_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new ServiceController(serviceService))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        SERVICE_RESPONSE = new ServiceResponseDto(SERVICE_ID);
        SERVICE_RESPONSE.setName("changing tires");
        SERVICE_RESPONSE.setPrice(10_000L);
        SERVICE_RESPONSE.setCarServiceId(1L);
    }

    @Test
    void getByIdMethod_shouldReturnService() throws Exception {
        when(serviceService.getById(SERVICE_ID)).thenReturn(SERVICE_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(SERVICE_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getByIdMethod_ifServiceNotFound_thenReturnNotFoundException() throws Exception {
        when(serviceService.getById(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMethod_shouldReturnAllServices() throws Exception {
        when(serviceService.getAll()).thenReturn(Collections.singletonList(SERVICE_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/services")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(SERVICE_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/services")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(SERVICE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(SERVICE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_ifServiceNotFound_thenReturnNotFoundException() throws Exception {
        when(serviceService.update(any(Long.class), any(ServiceRequestDto.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMethod_shouldDeleteService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_ifServiceNotFound_thenReturnNotFoundException() throws Exception {
        when(serviceService.delete(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(delete("/api/v1/services/1")
                        .param("id", String.valueOf(SERVICE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

}