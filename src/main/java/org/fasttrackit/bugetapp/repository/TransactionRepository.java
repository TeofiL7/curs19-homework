package org.fasttrackit.bugetapp.repository;

import org.fasttrackit.bugetapp.model.Transaction;
import org.fasttrackit.bugetapp.model.TransactionType;
import org.fasttrackit.bugetapp.model.filter.TransactionFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByMinAmount(TransactionFilter minAmount);
    List<Transaction> findByMaxAmount(TransactionFilter maxAmount);
    List<Transaction> findByTypeAndMin(TransactionType type, TransactionFilter minAmount);
    List<Transaction> findByTypeAndMax(TransactionType type, TransactionFilter maxAmount);
    List<Transaction> findByMinAndMax(TransactionFilter minAmount, TransactionFilter maxAmount);
    List<Transaction> findByTypeAndMinAndMax(TransactionType type ,TransactionFilter minAmount, TransactionFilter maxAmount);

}