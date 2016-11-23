package com.polymec.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
}
