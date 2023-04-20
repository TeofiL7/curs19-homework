package org.fasttrackit.bugetapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Builder(toBuilder = true)
@With
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction{
        @Id
        @GeneratedValue
        private String id;
        @Column
        private String product;
        @Column
        private TransactionType type;
        @Column
        private Double amount;
}
