package com.polymec.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {    
    @Query("select a.role from Role a join a.user b where b.userName =?1")
    public List<String> findRoleByUserName(String username);
}
