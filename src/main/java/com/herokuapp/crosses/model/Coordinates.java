package com.herokuapp.crosses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(exclude = "place")
@ToString(exclude = "place")
public class Coordinates {
    @JsonIgnore
    private Place place;
    private int x;
    private int y;
}