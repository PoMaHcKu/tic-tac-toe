package com.herokuapp.crosses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herokuapp.crosses.model.persist.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "game")
public class Gamer {

    private String login;
    private User user;
    @JsonIgnore
    private Game game;
    private PState mark;

    public void setUser(User user) {
        this.login = user.getLogin();
        this.user = user;
    }
}