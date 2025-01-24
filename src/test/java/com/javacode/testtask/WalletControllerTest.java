package com.javacode.testtask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacode.testtask.dto.OperationDto;
import com.javacode.testtask.dto.WalletDtoResponse;
import com.javacode.testtask.model.OperationType;
import com.javacode.testtask.model.Wallet;
import com.javacode.testtask.service.WalletService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
class WalletControllerTest {
    @MockBean
    private WalletService walletService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    private final UUID walletId = UUID.fromString("6269148b-ada9-4402-b578-e1a575075efc");
    WalletDtoResponse wallet = new WalletDtoResponse(walletId, 10000);

    @SneakyThrows
    @Test
    void testChangeBalance() {
        OperationDto operationDto = new OperationDto(walletId, OperationType.DEPOSIT, 5000);
        when(walletService.changeBalance(operationDto)).thenReturn(wallet);
        mockMvc.perform(post("/api/v1/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.balance").value(wallet.getBalance()))
                .andExpect(jsonPath("$.id").value(walletId.toString()));
        verify(walletService).changeBalance(any(OperationDto.class));
    }

    @SneakyThrows
    @Test
    void getById() {
        when(walletService.getById(walletId)).thenReturn(wallet);
        mockMvc.perform(get("/api/v1/wallets/{walletId}", walletId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(wallet.getBalance()))
                .andExpect(jsonPath("$.id").value(walletId.toString()));
        verify(walletService).getById(walletId);
    }
}