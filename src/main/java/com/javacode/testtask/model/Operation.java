package com.javacode.testtask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Column(name = "amount", nullable = false)
    private Long amount;
}
