package com.herokuapp.crosses.service.impl;

import com.herokuapp.crosses.exception.GameNotFoundException;
import com.herokuapp.crosses.mapper.IPrincipalMapper;
import com.herokuapp.crosses.model.Field;
import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.model.Gamer;
import com.herokuapp.crosses.model.Storage;
import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.service.IGameService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameService implements IGameService {

    private static final Map<String, Game> games = new Storage().getGames();
    private static int indexGame = 0;

    private final IPrincipalMapper principalMapper;

    public GameService(IPrincipalMapper principalMapper) {
        this.principalMapper = principalMapper;
    }

    @Override
    public Map<String, Game> getGames() {
        return games;
    }

    @Override
    public Game createGame(Authentication authentication) {
        Game game = new Game(indexGame++);
        User user = principalMapper.toUser(authentication);
        Gamer cross = new Gamer();
        cross.setUser(user);
        game.setField(new Field());
        game.setCross(cross);
        games.put(String.valueOf(game.getId()), game);
        return game;
    }

    @Override
    public Game joinGame(int id, Authentication authentication) {
        Game game = games.get(String.valueOf(id));
        if (game == null) {
            throw new GameNotFoundException(String.format("Game with id - %d not found.", id));
        }
        User user = principalMapper.toUser(authentication);
        if (user.getLogin().equals(game.getCross().getLogin())) {
            return game;
        }
        Gamer zero = new Gamer();
        zero.setUser(user);
        game.setZero(zero);
        return game;
    }
}