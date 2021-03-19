package com.herokuapp.crosses.controller.ws;

import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.service.IGameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.Map;

import static com.herokuapp.crosses.WebConstants.GAMES_ENDPOINT;

@Controller
public class GamesSocketController {

    private final IGameService gameService;

    public GamesSocketController(IGameService gameService) {
        this.gameService = gameService;
    }

    @SubscribeMapping(GAMES_ENDPOINT)
    public Map<String, Game> sendOneTimeMessage() {
        //checking solution
        Map<String, Game> games = gameService.getGames();
        if (games.isEmpty()) {
            return Collections.singletonMap("id", new Game(1));
        }
        return games;
    }

    @MessageMapping("/games")
    @SendTo("/topic/games")
    public Map<String, Game> getGames() {
        return gameService.getGames();
    }


}