package com.herokuapp.crosses.model;

import com.herokuapp.crosses.utils.PlaceComparator;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
public class Field {
    private Set<Place> places;

    public Field() {
        places = new TreeSet<>(PlaceComparator::byCoordinates) {{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Place place = Place.builder()
                            .state(PState.EMPTY)
                            .build();
                    place.setCoordinates(Coordinates.builder()
                            .x(i)
                            .y(j)
                            .build());
                    add(place);
                }
            }
        }};
    }
}