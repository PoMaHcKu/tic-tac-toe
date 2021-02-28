package com.herokuapp.crosses.service.impl;

import com.herokuapp.crosses.exception.GameNotFoundException;
import com.herokuapp.crosses.model.*;
import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.service.IGameService;
import com.herokuapp.crosses.service.IGamerService;
import com.herokuapp.crosses.utils.IPrincipalUtils;
import com.herokuapp.crosses.utils.WinCombinations;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {

    private static final Map<String, Game> games = new Storage().getGames();
    private static int indexGame = 0;

    private final IPrincipalUtils principalUtils;
    private final IGamerService gamerService;

    public GameService(IPrincipalUtils principalUtils,
                       IGamerService gamerService) {
        this.principalUtils = principalUtils;
        this.gamerService = gamerService;
    }

    @Override
    public Map<String, Game> getGames() {
        return games;
    }

    @Override
    public Game createGame() {
        Game game = new Game(indexGame++);
        User user = principalUtils.getAuthUser();
        Gamer cross = new Gamer();
        cross.setUser(user);
        game.setField(new Field());
        game.setCross(cross);
        games.put(String.valueOf(game.getId()), game);
        return game;
    }

    @Override
    public Game joinGame(int id) {
        Game game = games.get(String.valueOf(id));
        if (game == null) {
            throw new GameNotFoundException(String.format("Game with id - %d not found.", id));
        }
        User user = principalUtils.getAuthUser();
        if (user.getLogin().equals(game.getCross().getLogin())) {
            return game;
        }
        Gamer zero = new Gamer();
        zero.setUser(user);
        game.setZero(zero);
        game.setState(GameState.STARTED);
        game.setLastStep(PState.ZERO);
        return game;
    }

    @Override
    public GameResponse doStep(int id, Coordinates target) {
        Game game = games.get(String.valueOf(id));
        if (game == null) {
            throw new GameNotFoundException(String.format("Game with id - %d not found.", id));
        }
        if (!game.getState().equals(GameState.STARTED)) {
            throw new RuntimeException("Game is not started, or already end");
        }
        Gamer currentGamer = gamerService.getCurrentGamer(game);
        if (game.getLastStep().equals(currentGamer.getMark())) {
            throw new RuntimeException("Invalid queue");
        } else {
            game.setLastStep(currentGamer.getMark());
        }
        Place changedPlace = game.getField().getPlaces().stream().filter(place -> {
            if (place.getCoordinates().equals(target)) {
                if (!place.getState().equals(PState.EMPTY)) {
                    return false;
                }
                place.setState(currentGamer.getMark());
                return true;
            }
            return false;
        }).findAny().orElseThrow(() -> new RuntimeException("Target already marked"));
        game.getField().getPlaces().add(changedPlace);

        GameResponse gameResponse = new GameResponse(game);

        if (checkWin(game, currentGamer.getMark()).equals(GameState.END)) {
            game.setState(GameState.END);
            gameResponse.setWinner(currentGamer);
            games.remove(String.valueOf(game.getId()));
        }
        return gameResponse;
    }

    private GameState checkWin(Game game, PState checkingState) {
        Set<Coordinates> stateCoordinates = getPlaces(checkingState, game).stream()
                .map(Place::getCoordinates)
                .collect(Collectors.toSet());
        for (Set<Coordinates> winCombination : WinCombinations.allWinCombinations) {
            if (winCombination.containsAll(stateCoordinates))
                return GameState.END;
        }
        return GameState.STARTED;
    }

    private Set<Place> getPlaces(PState pState, Game game) {
        return game.getField().getPlaces().stream()
                .filter(place -> place.getState().equals(pState))
                .collect(Collectors.toSet());
    }

    @Data
    public static class GameResponse {
        private Game game;
        private Gamer winner;

        public GameResponse(Game game) {
            this.game = game;
        }
    }
}