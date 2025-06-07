package com.jphilips.onlineshop.cart.repository;

import com.jphilips.onlineshop.cart.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Page<CartItem> findByUserId (Long userId, Pageable pageable);

    Optional<CartItem> findByUserIdAndItemId(Long userId, Long itemId);

}
