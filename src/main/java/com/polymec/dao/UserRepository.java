package com.polymec.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
}
