package org.fasttrackit.bugetapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.bugetapp.model.Transaction;
import org.fasttrackit.bugetapp.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.fasttrackit.bugetapp.model.TransactionType.BUY;
import static org.fasttrackit.bugetapp.model.TransactionType.SELL;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandRunner implements CommandLineRunner {
    private final TransactionService service;

    @Override
    public void run(String... args) {
        log.info("Runner is populating with data");
        service.getAll(null).addAll(
                List.of(
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("laptop")
                                .type(BUY)
                                .amount(4000D)
                                .build(),
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("phone")
                                .type(SELL)
                                .amount(2000D)
                                .build(),
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("iPhone")
                                .type(SELL)
                                .amount(2400D)
                                .build(),
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("Car")
                                .type(BUY)
                                .amount(75000D)
                                .build(),
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("Ball")
                                .type(BUY)
                                .amount(100D)
                                .build(),
                        Transaction.builder()
                                .id(randomUUID().toString())
                                .product("Table")
                                .type(SELL)
                                .amount(20D)
                                .build()
                ));
    }
}
