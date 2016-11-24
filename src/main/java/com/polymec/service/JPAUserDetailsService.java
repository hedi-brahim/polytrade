package com.polymec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import com.polymec.dao.UserRepository;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;
import com.polymec.dao.RoleRepository;

@Service("jpaUserDetailsService")
public class JPAUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(JPAUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Collection<? extends GrantedAuthority> listAuthorities(String username) {
        List<String> userRoles = roleRepository.findRoleByUserName(username);
        for(String rl : userRoles) {
            System.out.println(rl);
            log.info(username + " tayara has role: " + rl);
        }
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.polymec.domain.User user = userRepository.findByUserName(username);
        if (null == user) {
            log.info("no user present with username: " + username);
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            log.info("user effectively present with username: " + username);
            log.info("user effectively present with password: " + user.getPassword());
            
            return new User(user.getUserName(),user.getPassword(),listAuthorities(username));
        }
    }

}
