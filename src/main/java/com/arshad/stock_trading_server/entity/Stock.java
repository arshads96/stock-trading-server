package com.arshad.stock_trading_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks", schema = "stocktrading")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_symbol", unique = true, nullable = false)
    private String symbol;

    @Column(nullable = false)
    private double price;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    private String currency;
}
