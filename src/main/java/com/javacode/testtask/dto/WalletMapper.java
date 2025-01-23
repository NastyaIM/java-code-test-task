package com.javacode.testtask.dto;

import com.javacode.testtask.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDtoResponse toWalletDtoResponse(Wallet wallet);
}
