package com.herokuapp.crosses.repository;

import com.herokuapp.crosses.model.persist.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
