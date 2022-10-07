package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.CarServiceController;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.service.CarServiceService;
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

@WebMvcTest(CarServiceController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarServiceControllerTest {

    @MockBean
    private CarServiceService carServiceService;

    private CarServiceResponseDto CAR_SERVICE_RESPONSE;

    private MockMvc mockMvc;

    private static final Long CAR_SERVICE_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new CarServiceController(carServiceService)).build();

        CAR_SERVICE_RESPONSE = new CarServiceResponseDto(CAR_SERVICE_ID);
        CAR_SERVICE_RESPONSE.setName("New car service");
        CAR_SERVICE_RESPONSE.setBudget(1_000_000L);
    }

    @Test
    void getByIdMethod_shouldReturnCarService() throws Exception {
        when(carServiceService.getById(CAR_SERVICE_ID)).thenReturn(CAR_SERVICE_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/carservices/1")
                        .param("id", String.valueOf(CAR_SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CAR_SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(CAR_SERVICE_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllMethod_shouldReturnAllCarServices() throws Exception {
        when(carServiceService.getAll()).thenReturn(Collections.singletonList(CAR_SERVICE_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/carservices")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CAR_SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(CAR_SERVICE_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateCarService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/carservices")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CAR_SERVICE_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(CAR_SERVICE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateCarService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/carservices/1")
                        .param("id", String.valueOf(CAR_SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CAR_SERVICE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(CAR_SERVICE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_shouldDeleteCarService() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/carservices/1")
                        .param("id", String.valueOf(CAR_SERVICE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

}
