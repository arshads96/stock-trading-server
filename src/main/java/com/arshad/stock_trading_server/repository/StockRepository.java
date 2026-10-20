package com.arshad.stock_trading_server.repository;

import com.arshad.stock_trading_server.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    Optional<Stock> findBySymbol(String symbol);
}
