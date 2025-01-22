package com.javacode.testtask.model;

import jakarta.persistence.*;
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
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "balance", nullable = false)
    private Long balance;
}
