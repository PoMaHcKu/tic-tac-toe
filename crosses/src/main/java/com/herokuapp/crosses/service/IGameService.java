package com.herokuapp.crosses.service;

import com.herokuapp.crosses.model.Game;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface IGameService {
    Game createGame(Authentication authentication);

    Map<String, Game> getGames();

    Game joinGame(int id, Authentication authentication);
}