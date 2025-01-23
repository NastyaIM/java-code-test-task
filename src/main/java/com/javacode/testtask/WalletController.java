package com.javacode.testtask;

import com.javacode.testtask.dto.OperationDto;
import com.javacode.testtask.dto.WalletDtoResponse;
import com.javacode.testtask.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDtoResponse changeBalance(@Valid @RequestBody OperationDto operationDto) {
        return walletService.changeBalance(operationDto);
    }

    @GetMapping("/{walletId}")
    public WalletDtoResponse getById(@PathVariable UUID walletId) {
        return walletService.getById(walletId);
    }
}
