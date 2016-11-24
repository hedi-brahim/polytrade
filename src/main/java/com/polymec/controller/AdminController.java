/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.controller;

import com.polymec.dao.RoleRepository;
import com.polymec.domain.User;
import com.polymec.dao.UserRepository;
import com.polymec.domain.Role;
import com.polymec.domain.UserRole;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Hedi
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;
    
    @ModelAttribute("allRoles")
    public List<Role> populateFeatures() {
        return Arrays.asList(Role.ALL);
    }
    
    /**
     * Registration page.
     */
    @GetMapping("/register")
    public String index(@ModelAttribute User user) {
        return "admin/registration";
    }
    
    @PostMapping("/register")
    public String saveNewUser(@ModelAttribute User user,BindingResult result)
    {
        if(result.hasErrors())
        {
            return "admin/registration";
        }
        
        for(UserRole role: user.getRoles())
        {
            role.setUser(user);
        }
        
        userRepository.save(user);
        return "admin/complete";
        //return "complete";

    }
  
}
