package com.herokuapp.crosses.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Storage {
    private final Map<String, Game> games = new HashMap<>();
}