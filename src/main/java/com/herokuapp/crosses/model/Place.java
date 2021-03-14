package com.herokuapp.crosses.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "coordinates")
@Builder
public class Place {
    private Coordinates coordinates;
    private PState state;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        coordinates.setPlace(this);
    }
}