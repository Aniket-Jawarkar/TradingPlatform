package com.aniket.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet() {
    }

    public Wallet(Long id, User user, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.balance = balance;
    }
}
