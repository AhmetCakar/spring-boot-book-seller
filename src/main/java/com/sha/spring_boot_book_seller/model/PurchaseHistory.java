package com.sha.spring_boot_book_seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "book_id", nullable = false)
    private long bookId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;

}
