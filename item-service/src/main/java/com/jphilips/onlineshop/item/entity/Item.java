package com.jphilips.onlineshop.item.entity;

import com.jphilips.onlineshop.item.enums.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private String sellerName;

    @Column(nullable = false)
    private String sellerEmail;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int stocks;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "Boolean")
    private boolean isActive;

    private String brand;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
