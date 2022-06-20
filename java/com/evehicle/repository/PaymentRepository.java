package com.evehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

}
