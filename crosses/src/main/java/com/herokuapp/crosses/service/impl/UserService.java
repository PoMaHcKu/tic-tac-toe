package com.herokuapp.crosses.service.impl;

import com.herokuapp.crosses.jwt.JwtProvider;
import com.herokuapp.crosses.model.persist.Role;
import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.repository.UserRepo;
import com.herokuapp.crosses.service.IUserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtProvider provider;


    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo, JwtProvider provider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.provider = provider;
    }

    @Override
    public User create(User user) {
        user.setRole(Role.PLAYER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User authenticate(User user) {
        User credentials = getByLoginAndPassword(user.getLogin(), user.getPassword());
        if (credentials != null) {
            credentials.setToken(provider.generateToken(credentials.getLogin()));
            return credentials;
        }
        throw new AuthenticationCredentialsNotFoundException("Login or password is not true");
    }

    @Override
    public User getByLoginAndPassword(String login, String pass) {
        User user = (User) loadUserByUsername(login);
        if (user != null && passwordEncoder.matches(pass, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByLogin(s);
    }
}
