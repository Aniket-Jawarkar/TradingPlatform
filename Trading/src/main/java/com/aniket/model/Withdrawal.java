package com.aniket.model;

import com.aniket.domain.WithdrawalStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private WithdrawalStatus status;
    private Long amount;
    @ManyToOne
    private User user;
    private LocalDateTime date = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WithdrawalStatus getStatus() {
        return status;
    }

    public void setStatus(WithdrawalStatus status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Withdrawal() {
    }

    public Withdrawal(Long id, WithdrawalStatus status, Long amount, User user, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.user = user;
        this.date = date;
    }
}
