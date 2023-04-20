package org.fasttrackit.bugetapp.service;

import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.bugetapp.exceptions.ResourceNotFoundException;
import org.fasttrackit.bugetapp.model.Transaction;
import org.fasttrackit.bugetapp.model.TransactionType;
import org.fasttrackit.bugetapp.model.filter.TransactionFilter;
import org.fasttrackit.bugetapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository=repository;

    }

    public List<Transaction> getAll(TransactionFilter filter) {
        if (filter == null) {
            return repository.findAll();
        }
        Stream<Transaction> transactionStream = repository.findAll().stream();
        if (filter.product() != null) {
            transactionStream = transactionStream.filter(transaction -> filter.product().equals(transaction.getProduct()));
        }
        if (filter.type() != null) {
            transactionStream = transactionStream.filter(transaction -> filter.type().equals(transaction.getType()));
        }
        if (filter.minAmount() != null) {
            transactionStream = transactionStream.filter(transaction -> filter.minAmount() < transaction.getAmount());
        }
        if (filter.maxAmount() != null) {
            transactionStream = transactionStream.filter(transaction -> filter.maxAmount() > transaction.getAmount());
        }
        return transactionStream.toList();
    }

    public Map<TransactionType, List<Transaction>> getGroupedByType() {
        return repository.findAll().stream().collect(groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getByProduct() {
        return repository.findAll().stream().collect(groupingBy(Transaction::getProduct));
    }

    public Transaction getTransactionWithId(String id) throws ResourceNotFoundException {
        return repository.findById(String.valueOf(id))
                .orElseThrow(()-> new ResourceNotFoundException("Country with id %s was not found".formatted(id)));
    }

    public Transaction addNewTransaction(Transaction addTransaction) {
        return repository.save(addTransaction);
    }

    public Transaction deletedWithIdd(String id) {
        Transaction deleteTransaction = getTransactionWithId(id);
        repository.deleteById(String.valueOf(id));
        return deleteTransaction;
    }

    public Transaction replaceTransaction(String id, Transaction replaceTransaction) {
        Transaction foundTransaction = getTransactionWithId(id);
        deletedWithIdd(id);
        Transaction updatedTransaction = foundTransaction.toBuilder()
                .id(foundTransaction.getId())
                .product(replaceTransaction.getProduct())
                .type(replaceTransaction.getType())
                .amount(replaceTransaction.getAmount())
                .build();
        repository.save(replaceTransaction);
        return updatedTransaction;
    }

    public List<Transaction> getFindByType(TransactionType type){
        return repository.findByType(type);
    }
    public List<Transaction> getFindByMinAmount(TransactionFilter minAmount){
        return repository.findByMinAmount(minAmount);
    }
    public List<Transaction> getFindByMaxAmount(TransactionFilter maxAmount){
        return repository.findByMaxAmount(maxAmount);
    }
    public List<Transaction> getFindByTypeAndMin(TransactionType type, TransactionFilter minAmount){
        return repository.findByTypeAndMin(type,minAmount);
    }
    public List<Transaction> getFindByTypeAndMax(TransactionType type, TransactionFilter maxAmount){
        return repository.findByTypeAndMax(type,maxAmount);
    }
    public List<Transaction> getFindByMinAndMax(TransactionFilter minAmount, TransactionFilter maxAmount){
        return repository.findByMinAndMax(minAmount, maxAmount);
    }
    public List<Transaction> getByTypeAndMinAndMax(TransactionType type, TransactionFilter minAmount, TransactionFilter maxAmount){
        return repository.findByTypeAndMinAndMax(type, minAmount, maxAmount);
    }


}
