package com.javacode.testtask.dto;

import com.javacode.testtask.model.Wallet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WalletMapper {
    WalletDtoResponse toWalletDtoResponse(Wallet wallet);
}
