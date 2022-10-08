package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.OrderStatusController;
import com.aston.carservice.controller.handler.GlobalExceptionHandler;
import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.OrderStatusService;
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

@WebMvcTest(OrderStatusController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderStatusControllerTest {

    @MockBean
    private OrderStatusService orderStatusService;

    private OrderStatusResponseDto ORDER_STATUS_RESPONSE;

    private MockMvc mockMvc;

    private static final Long ORDER_STATUS_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new OrderStatusController(orderStatusService))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        ORDER_STATUS_RESPONSE = new OrderStatusResponseDto(ORDER_STATUS_ID);
        ORDER_STATUS_RESPONSE.setName("PENDING");
    }

    @Test
    void getByIdMethod_shouldReturnOrderStatus() throws Exception {
        when(orderStatusService.getById(ORDER_STATUS_ID)).thenReturn(ORDER_STATUS_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ORDER_STATUS_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getByIdMethod_ifOrderStatusNotFound_thenReturnNotFoundException() throws Exception {
        when(orderStatusService.getById(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMethod_shouldReturnAllOrderStatuses() throws Exception {
        when(orderStatusService.getAll()).thenReturn(Collections.singletonList(ORDER_STATUS_RESPONSE));
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/orders/statuses")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(ORDER_STATUS_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateOrderStatus() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/orders/statuses")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(ORDER_STATUS_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateCarOrderStatus() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ORDER_STATUS_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_ifOrderStatusNotFound_thenReturnNotFoundException() throws Exception {
        when(orderStatusService.update(any(Long.class), any(OrderStatusRequestDto.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMethod_shouldDeleteOrderStatus() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_ifOrderStatusNotFound_thenReturnNotFoundException() throws Exception {
        when(orderStatusService.delete(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(delete("/api/v1/orders/statuses/1")
                        .param("id", String.valueOf(ORDER_STATUS_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_STATUS_RESPONSE)))
                .andExpect(status().isNotFound());
    }

}
