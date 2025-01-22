package com.javacode.testtask.dto;

import com.javacode.testtask.model.Operation;
import com.javacode.testtask.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    OperationDto toOperationDto(Operation operation);
    Operation toOperation(OperationDto operationDto);
}
