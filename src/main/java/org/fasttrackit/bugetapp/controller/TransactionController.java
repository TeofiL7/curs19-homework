package org.fasttrackit.bugetapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.bugetapp.exceptions.ResourceNotFoundException;
import org.fasttrackit.bugetapp.model.Transaction;
import org.fasttrackit.bugetapp.model.TransactionType;
import org.fasttrackit.bugetapp.model.filter.TransactionFilter;
import org.fasttrackit.bugetapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService service;

    @GetMapping
    public List<Transaction> getAllTransactions(TransactionFilter filter) {
        log.info("GET all transactions");
        return service.getAll(filter);
    }

    @GetMapping("{id}")
    public Transaction getTransactionWithId(@PathVariable String id){
        log.info("GET transactions with id");
        return service.getTransactionWithId(id);
    }

    @PostMapping
    public Transaction addsNewTransaction(@RequestBody Transaction addTransaction){
        log.info("POST add transactions");
        return service.addNewTransaction(addTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deletedWithIdd(@PathVariable String id){
        log.info("DELETE transaction with id");
        return service.deletedWithIdd(id);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable String id, @RequestBody Transaction replaceTransaction) {
        log.info("PUT replace transaction");
        return service.replaceTransaction(id, replaceTransaction);
    }

    @GetMapping
    public List<Transaction> getFindByType(TransactionType type){
        log.info("GET all transactions by type");
        return service.getFindByType(type);
    }

    @GetMapping
    public List<Transaction> getFindByMinAmount(TransactionFilter minAmount){
        log.info("GET all transactions by minAmount");
        return service.getFindByMinAmount(minAmount);
    }

    @GetMapping
    public List<Transaction> getFindByMaxAmount(TransactionFilter maxAmount){
        log.info("GET all transactions by type");
        return service.getFindByMaxAmount(maxAmount);
    }

    @GetMapping
    public List<Transaction> getFindByTypeAndMin(TransactionType type, TransactionFilter minAmount){
        log.info("GET all transactions by type and minAmount");
        return service.getFindByTypeAndMin(type,minAmount);
    }

    @GetMapping
    public List<Transaction> getFindByTypeAndMax(TransactionType type, TransactionFilter maxAmount){
        log.info("GET all transactions by type and maxAmount");
        return service.getFindByTypeAndMax(type,maxAmount);
    }

    @GetMapping
    public List<Transaction> getFindByMinAndMax(TransactionFilter minAmount, TransactionFilter maxAmount){
        log.info("GET all transactions by minAmount and maxAmount");
        return service.getFindByMinAndMax(minAmount, maxAmount);
    }

    @GetMapping
    public List<Transaction> getByTypeAndMinAndMax(TransactionType type, TransactionFilter minAmount, TransactionFilter maxAmount){
        log.info("GET all transactions by type, minAmount and maxAmount");
        return service.getByTypeAndMinAndMax(type, minAmount, maxAmount);
    }
}
