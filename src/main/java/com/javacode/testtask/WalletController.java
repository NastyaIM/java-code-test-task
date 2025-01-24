package com.javacode.testtask;

import com.javacode.testtask.dto.OperationDto;
import com.javacode.testtask.dto.WalletDtoResponse;
import com.javacode.testtask.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDtoResponse changeBalance(@Valid @RequestBody OperationDto operationDto) {
        WalletDtoResponse walletDtoResponse = walletService.changeBalance(operationDto);
        log.debug("Изменяем баланс {}", walletDtoResponse);
        return walletDtoResponse;
    }

    @GetMapping("/{walletId}")
    public WalletDtoResponse getById(@PathVariable UUID walletId) {
        return walletService.getById(walletId);
    }
}
