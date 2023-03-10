package org.marsrover.enums;

public enum Direction {

    N("North"), E("East"), W("West"), S("South");

    String direction;
    Direction(String direction){
        this.direction=direction;
    }
}
