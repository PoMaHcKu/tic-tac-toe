package com.herokuapp.crosses;

import java.util.Arrays;
import java.util.List;

public interface WebConstants {

    List<String> ALLOW_ORIGINS = Arrays.asList("http://localhost:3000", "https://localhost:3000");
    String SEPARATOR = "/";
    String GAMES_ENDPOINT = "/games";
    String TOPIC_BROKER_ENDPOINT = "/topic";
    String APP_BROKER_ENDPOINT = "/app";
    String REGISTER_ENDPOINT = "/register";
    String LOGIN_ENDPOINT = "/login";
    String GAME_ENDPOINT = "/game";
    String NEW_ENDPOINT = "/new";
    String JOIN_ENDPOINT = "/login";
    String STEP_ENDPOINT = "/login";
}