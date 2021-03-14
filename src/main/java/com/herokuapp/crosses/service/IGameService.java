package com.herokuapp.crosses.service;

import com.herokuapp.crosses.model.Coordinates;
import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.service.impl.GameService;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface IGameService {
    Game createGame();

    Map<String, Game> getGames();

    Game joinGame(int id);

    GameService.GameResponse doStep(int id, Coordinates target);
}