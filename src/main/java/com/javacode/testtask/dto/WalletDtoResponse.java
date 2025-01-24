package com.javacode.testtask.dto;

import com.javacode.testtask.model.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WalletDtoResponse {
    private UUID id;
    @PositiveOrZero
    private long balance;
}
