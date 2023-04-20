package org.fasttrackit.bugetapp.model.filter;


import lombok.Builder;
import org.fasttrackit.bugetapp.model.TransactionType;

@Builder
public record TransactionFilter(
        String product,
        TransactionType type,
        Double minAmount,
        Double maxAmount
) {
}
