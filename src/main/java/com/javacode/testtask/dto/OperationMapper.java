package com.javacode.testtask.dto;

import com.javacode.testtask.model.Operation;
import com.javacode.testtask.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @Mapping(target = "wallet.id", source = "walletId")
    @Mapping(target = "id", ignore = true)
    Operation toOperation(OperationDto operationDto);
}
