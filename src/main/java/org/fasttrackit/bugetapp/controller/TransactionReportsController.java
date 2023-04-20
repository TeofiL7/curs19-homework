package org.fasttrackit.bugetapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.bugetapp.model.Transaction;
import org.fasttrackit.bugetapp.model.TransactionType;
import org.fasttrackit.bugetapp.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions/reports")
@RequiredArgsConstructor
@Slf4j
public class TransactionReportsController {
    private final TransactionService service;

    @GetMapping("type")
    public Map<TransactionType, List<Transaction>> getGroupedByType() {
        return service.getGroupedByType();
    }

    @GetMapping("product")
    public Map<String, List<Transaction>> getByProduct(){
        return service.getByProduct();
    }
}
