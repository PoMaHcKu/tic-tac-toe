package com.herokuapp.crosses.mapper.impl;

import com.herokuapp.crosses.mapper.IPrincipalMapper;
import com.herokuapp.crosses.model.persist.Role;
import com.herokuapp.crosses.model.persist.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

@Service
public class PrincipalMapper implements IPrincipalMapper {
    @Override
    public User toUser(Authentication authentication) {
        if (authentication == null) return null;
        UserDetails details =  (UserDetails) authentication.getPrincipal();
        Iterator<? extends GrantedAuthority> authorities = details.getAuthorities().iterator();
        Role role = authorities.hasNext() ? Role.valueOf(authorities.next().getAuthority()) : null;
        return User.builder()
                .login(details.getUsername())
                .role(role)
                .build();
    }
}
