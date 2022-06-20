package com.evehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

}
