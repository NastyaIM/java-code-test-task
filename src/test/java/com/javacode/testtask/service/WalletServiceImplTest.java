package com.javacode.testtask.service;

import com.javacode.testtask.dto.*;
import com.javacode.testtask.exceptions.InsufficientFundsException;
import com.javacode.testtask.exceptions.NotFoundException;
import com.javacode.testtask.model.Operation;
import com.javacode.testtask.model.OperationType;
import com.javacode.testtask.model.Wallet;
import com.javacode.testtask.repository.OperationRepository;
import com.javacode.testtask.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private WalletServiceImpl walletService;
    @Mock
    private OperationRepository operationRepository;
    @Spy
    private WalletMapper walletMapper = Mappers.getMapper(WalletMapper.class);
    @Spy
    private OperationMapper operationMapper = Mappers.getMapper(OperationMapper.class);
    private final UUID walletId = UUID.fromString("6269148b-ada9-4402-b578-e1a575075efc");

    @Test
    void testChangeBalanceDeposit() {
        OperationDto operationDto = new OperationDto(walletId, OperationType.DEPOSIT, 5000);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(new Wallet(walletId, 3000L)));

        walletService.changeBalance(operationDto);
        assertEquals(8000, walletService.getById(walletId).getBalance());
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    void testChangeBalanceWithDrawIfBalanceGreaterThanAmount() {
        OperationDto operationDto = new OperationDto(walletId, OperationType.WITHDRAW, 5000);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(new Wallet(walletId, 8000L)));

        walletService.changeBalance(operationDto);
        assertEquals(3000L, walletService.getById(walletId).getBalance());
    }

    @Test
    void testChangeBalanceWithDrawIfBalanceIsLessThanAmount() {
        OperationDto operationDto = new OperationDto(walletId, OperationType.WITHDRAW, 5000);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(new Wallet(walletId, 3000L)));

        assertThrows(InsufficientFundsException.class, () -> walletService.changeBalance(operationDto));
    }

    @Test
    void testGetByIdIfWalletExist() {
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(new Wallet(walletId, 3000L)));
        WalletDtoResponse walletDtoResponse = walletService.getById(walletId);
        assertEquals(walletId, walletDtoResponse.getId());
        assertEquals(3000L, walletDtoResponse.getBalance());
    }

    @Test
    void testGetByIdIfWalletIsNotExist() {
        assertThrows(NotFoundException.class, () -> walletService.getById(walletId));
    }
}