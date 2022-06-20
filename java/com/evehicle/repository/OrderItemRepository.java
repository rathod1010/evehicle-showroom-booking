package com.evehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {

}
