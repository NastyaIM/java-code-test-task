package com.javacode.testtask.dto;

import com.javacode.testtask.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
//    //WalletDto toWalletDto(Wallet wallet);
//    Wallet toWallet( operationDto);
    WalletDtoResponse toWalletDtoResponse(Wallet wallet);
}
