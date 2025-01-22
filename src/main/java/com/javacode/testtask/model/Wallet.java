package com.javacode.testtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @Column(name = "wallet_id", nullable = false, unique = true)
    private UUID walletId;
    @Column(name = "operationType", nullable = false)
    private OperationType operationType;
    @Column(name = "amount", nullable = false)
    private Long amount;
}
