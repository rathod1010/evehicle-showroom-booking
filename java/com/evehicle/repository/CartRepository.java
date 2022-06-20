package com.evehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {

}
