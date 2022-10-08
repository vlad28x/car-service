package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.ConsumableController;
import com.aston.carservice.controller.handler.GlobalExceptionHandler;
import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.ConsumableService;
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

@WebMvcTest(ConsumableController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsumableControllerTest {

    @MockBean
    private ConsumableService consumableService;

    private ConsumableResponseDto CONSUMABLE_RESPONSE;

    private MockMvc mockMvc;

    private static final Long CONSUMABLE_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new ConsumableController(consumableService))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        CONSUMABLE_RESPONSE = new ConsumableResponseDto(CONSUMABLE_ID);
        CONSUMABLE_RESPONSE.setName("gloves");
        CONSUMABLE_RESPONSE.setPrice(100L);
        CONSUMABLE_RESPONSE.setQuantity(100L);
    }

    @Test
    void getByIdMethod_shouldReturnConsumable() throws Exception {
        when(consumableService.getById(CONSUMABLE_ID)).thenReturn(CONSUMABLE_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(CONSUMABLE_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getByIdMethod_ifConsumableNotFound_thenReturnNotFoundException() throws Exception {
        when(consumableService.getById(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMethod_shouldReturnAllConsumables() throws Exception {
        when(consumableService.getAll()).thenReturn(Collections.singletonList(CONSUMABLE_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/consumables")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(CONSUMABLE_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/consumables")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(CONSUMABLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(CONSUMABLE_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_ifConsumableNotFound_thenReturnNotFoundException() throws Exception {
        when(consumableService.update(any(Long.class), any(ConsumableRequestDto.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMethod_shouldDeleteConsumable() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_ifConsumableNotFound_thenReturnNotFoundException() throws Exception {
        when(consumableService.delete(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(delete("/api/v1/consumables/1")
                        .param("id", String.valueOf(CONSUMABLE_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CONSUMABLE_RESPONSE)))
                .andExpect(status().isNotFound());
    }

}
