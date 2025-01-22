package com.javacode.testtask;

import com.javacode.testtask.model.OperationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private UUID walletId;
    private OperationType operationType;
    private long amount;
}
