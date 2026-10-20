package com.arshad.stock_trading_server.service;


import com.arshad.grpc.StockRequest;
import com.arshad.grpc.StockResponse;
import com.arshad.grpc.StockTradingServiceGrpc;
import com.arshad.stock_trading_server.entity.Stock;
import com.arshad.stock_trading_server.repository.StockRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private StockRepository stockRepository;



    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        Stock stock = stockRepository.findBySymbol(request.getStockSymbol())
                .orElseThrow(() -> Status.NOT_FOUND
                        .withDescription("Stock not found: " + request.getStockSymbol())
                        .asRuntimeException());

        StockResponse response = StockResponse.newBuilder()
                .setStockSymbol(stock.getSymbol())
                .setStockPrice(stock.getPrice())
                .setCurrency(stock.getCurrency())
                .setTimestamp(stock.getLastUpdated().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
