package com.herokuapp.crosses.utils;

import com.herokuapp.crosses.model.Coordinates;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface WinCombinations {
    Set<Coordinates> first = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(0).build());
        add(Coordinates.builder().x(0).y(1).build());
        add(Coordinates.builder().x(0).y(2).build());
    }};
    Set<Coordinates> second = new HashSet<>(){{
        add(Coordinates.builder().x(1).y(0).build());
        add(Coordinates.builder().x(1).y(1).build());
        add(Coordinates.builder().x(1).y(2).build());
    }};
    Set<Coordinates> third = new HashSet<>(){{
        add(Coordinates.builder().x(2).y(0).build());
        add(Coordinates.builder().x(2).y(1).build());
        add(Coordinates.builder().x(2).y(2).build());
    }};
    Set<Coordinates> fourth = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(0).build());
        add(Coordinates.builder().x(1).y(0).build());
        add(Coordinates.builder().x(2).y(0).build());
    }};
    Set<Coordinates> fifth = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(1).build());
        add(Coordinates.builder().x(1).y(1).build());
        add(Coordinates.builder().x(2).y(1).build());
    }};
    Set<Coordinates> sixth = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(2).build());
        add(Coordinates.builder().x(1).y(2).build());
        add(Coordinates.builder().x(2).y(2).build());
    }};
    Set<Coordinates> seventh = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(0).build());
        add(Coordinates.builder().x(1).y(1).build());
        add(Coordinates.builder().x(2).y(2).build());
    }};
    Set<Coordinates> eighth = new HashSet<>(){{
        add(Coordinates.builder().x(0).y(2).build());
        add(Coordinates.builder().x(1).y(1).build());
        add(Coordinates.builder().x(2).y(0).build());
    }};

    List<Set<Coordinates>> allWinCombinations = List.of(eighth, seventh, sixth, fifth, fourth, third, second, first);

    Set<Coordinates> all = Stream.of(eighth, seventh, sixth, fifth, fourth, third, second, first)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
}