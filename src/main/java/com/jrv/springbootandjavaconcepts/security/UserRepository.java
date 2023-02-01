package com.jrv.springbootandjavaconcepts.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);

    @Query(value = "SELECT EXISTS (SELECT username FROM users WHERE username=?1)",nativeQuery = true)
    boolean checkIfUserExists(String username);
}
