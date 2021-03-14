package com.herokuapp.crosses.service;

import com.herokuapp.crosses.model.Game;
import com.herokuapp.crosses.model.Gamer;

public interface IGamerService {

    Gamer getCurrentGamer(Game game);
}