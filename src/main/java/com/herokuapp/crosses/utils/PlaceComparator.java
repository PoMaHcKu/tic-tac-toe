package com.herokuapp.crosses.utils;

import com.herokuapp.crosses.model.Place;

public class PlaceComparator {

    public static int byCoordinates(Place place, Place other) {
        int first = place.getCoordinates().getX() * 10 + place.getCoordinates().getY();
        int second = other.getCoordinates().getX() * 10 + other.getCoordinates().getY();
        return Integer.compare(first, second);
    }
}