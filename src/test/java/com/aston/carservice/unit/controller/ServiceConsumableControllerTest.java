package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.ServiceConsumableController;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.service.ServiceConsumableService;
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

@WebMvcTest(ServiceConsumableController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceConsumableControllerTest {

    @MockBean
    private ServiceConsumableService serviceConsumableService;

    private ServiceConsumableResponseDto SERVICE_CONSUMABLE_RESPONSE;

    private MockMvc mockMvc;

    private static final ServiceConsumableId SERVICE_CONSUMABLE_ID = new ServiceConsumableId(1L, 1L);

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new ServiceConsumableController(serviceConsumableService)).build();

        SERVICE_CONSUMABLE_RESPONSE = new ServiceConsumableResponseDto();
        SERVICE_CONSUMABLE_RESPONSE.setService(new ServiceResponseDto(1L));
        SERVICE_CONSUMABLE_RESPONSE.setConsumable(new ConsumableResponseDto(1L));
        SERVICE_CONSUMABLE_RESPONSE.setCount(4L);
    }

    @Test
    void getByIdMethod_shouldReturnServiceConsumable() throws Exception {
        when(serviceConsumableService.getById(SERVICE_CONSUMABLE_ID)).thenReturn(SERVICE_CONSUMABLE_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/services/1/consumables/1")
                        .param("serviceId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getService().getId()))
                        .param("consumableId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getConsumable().getId()))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(SERVICE_CONSUMABLE_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllMethod_shouldReturnAllServiceConsumables() throws Exception {
        when(serviceConsumableService.getAll()).thenReturn(Collections.singletonList(SERVICE_CONSUMABLE_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/services/consumables")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(SERVICE_CONSUMABLE_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateServiceConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/services/consumables")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_CONSUMABLE_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(SERVICE_CONSUMABLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateServiceConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/services/1/consumables/1")
                        .param("serviceId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getService().getId()))
                        .param("consumableId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getConsumable().getId()))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(SERVICE_CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(SERVICE_CONSUMABLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_shouldDeleteServiceConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/services/1/consumables/1")
                        .param("serviceId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getService().getId()))
                        .param("consumableId", String.valueOf(SERVICE_CONSUMABLE_RESPONSE.getConsumable().getId()))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

}
