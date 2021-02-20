package com.herokuapp.crosses.controller;

import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.service.IGameService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {

    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("new")
    public Game createNewGame(Authentication authentication) {
        UserDetails player = (UserDetails) authentication.getPrincipal();
        return gameService.createGame(authentication);
    }

    @GetMapping("join/{id}")
    public Game joinGame(@PathVariable int id, Authentication authentication) {
        return gameService.joinGame(id, authentication);
    }

    @GetMapping
    public Map<String, Game> getGames() {
        return gameService.getGames();
    }
}