package com.javacode.testtask.service;

import com.javacode.testtask.dto.OperationDto;
import com.javacode.testtask.dto.WalletDtoResponse;

import java.util.UUID;

public interface WalletService {
    WalletDtoResponse changeBalance(OperationDto operationDto);
    WalletDtoResponse getById(UUID walletId);
}
