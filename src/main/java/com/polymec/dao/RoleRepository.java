package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {    
    @Query("select a.role from UserRole a join a.user b where b.userName =?1")
    public List<String> findRoleByUserName(String username);
}
