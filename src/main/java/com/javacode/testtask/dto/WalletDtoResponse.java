package com.javacode.testtask.dto;

import com.javacode.testtask.model.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDtoResponse {
    private UUID id;
    @PositiveOrZero
    private long balance;
}
