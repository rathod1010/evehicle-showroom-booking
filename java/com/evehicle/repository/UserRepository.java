package com.evehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
