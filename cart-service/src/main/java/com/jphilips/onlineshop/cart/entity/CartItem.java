package com.jphilips.onlineshop.cart.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "itemId"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Integer count;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdateAt;
}
