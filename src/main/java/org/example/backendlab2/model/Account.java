package org.example.backendlab2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Person person;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    public Account() {
    }

    public Account(Person user, BigDecimal balance) {
        this.person = user;
        this.balance = balance;
    }

    public void addFunds(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deductFunds(BigDecimal amount, boolean allowNegativeBalance) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        if (!allowNegativeBalance && this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        this.balance = this.balance.subtract(amount);
    }
}