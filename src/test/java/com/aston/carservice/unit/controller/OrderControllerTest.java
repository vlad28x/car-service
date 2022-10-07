package com.aston.carservice.unit.controller;

import com.aston.carservice.controller.OrderController;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.dto.UserResponseDto;
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

    private MockMvc mockMvc;

    private static final Long ORDER_ID = 1L;

    @BeforeAll
    void setUp() {
        mockMvc = standaloneSetup(new OrderController(orderService)).build();

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
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v1/orders")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isCreated())
                .andReturn();
        verifyBody(asJsonString(ORDER_RESPONSE), mvcResult.getRequest().getContentAsString());
    }

    @Test
    void updateMethod_shouldUpdateCarOrder() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(put("/api/v1/orders/1")
                        .param("id", String.valueOf(ORDER_ID))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ORDER_RESPONSE)))
                .andExpect(status().isOk())
                .andReturn();
        verifyBody(asJsonString(ORDER_RESPONSE), mvcResult.getRequest().getContentAsString());
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

}
