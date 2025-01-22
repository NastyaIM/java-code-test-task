package com.javacode.testtask.service;

import com.javacode.testtask.dto.OperationDto;
import com.javacode.testtask.dto.OperationMapper;
import com.javacode.testtask.dto.WalletDtoResponse;
import com.javacode.testtask.dto.WalletMapper;
import com.javacode.testtask.exceptions.InsufficientFundsException;
import com.javacode.testtask.exceptions.NotFoundException;
import com.javacode.testtask.model.Operation;
import com.javacode.testtask.model.OperationType;
import com.javacode.testtask.model.Wallet;
import com.javacode.testtask.repository.OperationRepository;
import com.javacode.testtask.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final OperationRepository operationRepository;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final OperationMapper operationMapper;


    @Override
    public WalletDtoResponse changeBalance(OperationDto operationDto) {
        Wallet wallet = walletRepository.findById(operationDto.getWalletId())
                .orElseThrow(() -> new NotFoundException("Кошелька не существует"));
        changeBalance(operationDto, wallet);
        Operation operation = operationMapper.toOperation(operationDto);
        operation.setWallet(wallet);
        operationRepository.save(operation);
        return walletMapper.toWalletDtoResponse(walletRepository.save(wallet));
    }

    @Override
    public WalletDtoResponse getById(UUID walletId) {
        return walletMapper.toWalletDtoResponse(walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Кошелька не существует")));
    }

    private void changeBalance(OperationDto operationDto, Wallet wallet) {
        long amount = operationDto.getAmount();
        if (operationDto.getOperationType().equals(OperationType.DEPOSIT)) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else {
            if (wallet.getBalance() - amount < 0) {
                throw new InsufficientFundsException("Недостаточно средств на счете");
            } else {
                wallet.setBalance(wallet.getBalance() - amount);
            }
        }
    }
}
