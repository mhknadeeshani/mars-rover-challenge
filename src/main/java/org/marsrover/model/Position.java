package org.marsrover.model;

import java.util.Objects;

public class Position {

    private Coordinates coordinates;

    private String direction;

    public Position() {
    }

    public Position(Coordinates coordinates, String direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinates=" + coordinates +
                ", direction='" + direction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(coordinates, position.coordinates) && Objects.equals(direction, position.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, direction);
    }
}
