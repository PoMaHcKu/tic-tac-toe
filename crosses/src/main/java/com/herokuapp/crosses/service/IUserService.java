package com.herokuapp.crosses.service;

import com.herokuapp.crosses.model.persist.User;

public interface IUserService {

    User create(User user);

    User authenticate(User user);

    User getByLoginAndPassword(String login, String pass);
}