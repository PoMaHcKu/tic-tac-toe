package com.herokuapp.crosses.utils.impl;

import com.herokuapp.crosses.model.persist.Role;
import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.utils.IPrincipalUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class PrincipalUtils implements IPrincipalUtils {
    @Override
    public User toUser(Authentication authentication) {
        if (authentication == null) return null;
        UserDetails details = (UserDetails) authentication.getPrincipal();
        Iterator<? extends GrantedAuthority> authorities = details.getAuthorities().iterator();
        Role role = authorities.hasNext() ? Role.valueOf(authorities.next().getAuthority()) : null;
        if (role == null) {
            throw new AuthenticationCredentialsNotFoundException("Not found any role of user " + details.getUsername());
        }
        User user = new User();
        user.setLogin(details.getUsername());
        user.setPassword(details.getPassword());
        user.setRole(Role.valueOf(role.getAuthority()));
        return user;
    }

    @Override
    public User getAuthUser() {
        return toUser(SecurityContextHolder.getContext().getAuthentication());
    }
}
