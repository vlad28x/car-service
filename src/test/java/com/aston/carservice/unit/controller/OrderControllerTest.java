package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.OrderController;
import com.aston.carservice.controller.handler.GlobalExceptionHandler;
import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.OrderService;
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

@WebMvcTest(OrderController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    private OrderResponseDto ORDER_RESPONSE;

    private OrderRequestDto ORDER_REQUEST;

    private MockMvc mockMvc;

    private static final Long ORDER_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new OrderController(orderService))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        ORDER_REQUEST = new OrderRequestDto();
        ORDER_REQUEST.setStatusId(1L);
        ORDER_REQUEST.setCustomerId(1L);
        ORDER_REQUEST.setManagerId(2L);
        ORDER_REQUEST.setWorkerId(3L);
        ORDER_REQUEST.setServicesId(Collections.singletonList(1L));

        ORDER_RESPONSE = new OrderResponseDto(ORDER_ID);
        ORDER_RESPONSE.setPrice(10_000L);
        ORDER_RESPONSE.setStatus(new OrderStatusResponseDto(1L));
        ORDER_RESPONSE.setCustomer(new UserResponseDto(1L));
        ORDER_RESPONSE.setManager(new UserResponseDto(2L));
        ORDER_RESPONSE.setWorker(new UserResponseDto(3L));
        ORDER_RESPONSE.setServices(Collections.singletonList(new ServiceResponseDto(1L)));
    }

    @Test
    void getByIdMethod_shouldReturnOrder() throws Exception {
        when(orderService.getById(ORDER_ID)).thenReturn(ORDER_RESPONSE);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ORDER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getByIdMethod_ifOrderNotFound_thenReturnNotFoundException() throws Exception {
        when(orderService.getById(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMethod_shouldReturnAllOrders() throws Exception {
        when(orderService.getAll()).thenReturn(Collections.singletonList(ORDER_RESPONSE));

        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/orders")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(Collections.singletonList(ORDER_RESPONSE)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void createMethod_shouldCreateOrder() throws Exception {
        doReturn(ORDER_RESPONSE).when(orderService).create(any(OrderRequestDto.class));

        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/orders")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_REQUEST)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(ORDER_REQUEST), mvcResult.getRequest().getContentAsString());
        verifyBody(asJsonString(ORDER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateCarOrder() throws Exception {
        doReturn(ORDER_RESPONSE).when(orderService).update(any(Long.class), any(OrderRequestDto.class));

        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_REQUEST)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ORDER_REQUEST), mvcResult.getRequest().getContentAsString());
        verifyBody(asJsonString(ORDER_RESPONSE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateMethod_ifOrderNotFound_thenReturnNotFoundException() throws Exception {
        when(orderService.update(any(Long.class), any(OrderRequestDto.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_REQUEST)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMethod_shouldDeleteOrder() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(0)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(0), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void deleteMethod_ifOrderNotFound_thenReturnNotFoundException() throws Exception {
        when(orderService.delete(any(Long.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(delete("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isNotFound());
    }

}
