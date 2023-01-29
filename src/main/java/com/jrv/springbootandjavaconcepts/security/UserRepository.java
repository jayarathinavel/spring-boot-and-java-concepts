package com.jrv.springbootandjavaconcepts.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);
}
