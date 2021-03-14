package com.herokuapp.crosses.service.impl;

import com.herokuapp.crosses.exception.UnrecognizedGamerException;
import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.model.Gamer;
import com.herokuapp.crosses.service.IGamerService;
import com.herokuapp.crosses.utils.impl.PrincipalUtils;
import org.springframework.stereotype.Service;

@Service
public class GamerService implements IGamerService {

    private PrincipalUtils principalUtils;

    public GamerService(PrincipalUtils principalUtils) {
        this.principalUtils = principalUtils;
    }

    @Override
    public Gamer getCurrentGamer(Game game) {
        String gamerLogin = principalUtils.getAuthUser().getLogin();
        if (game.getCross().getLogin().equals(gamerLogin)) {
            return game.getCross();
        } else if (game.getZero().getLogin().equals(gamerLogin)) {
            return game.getZero();
        } else {
            throw new UnrecognizedGamerException(String.format("In this game no users with login %s", gamerLogin));
        }
    }
}
