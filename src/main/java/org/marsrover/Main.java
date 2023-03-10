package org.marsrover;

import lombok.extern.log4j.Log4j2;
import org.marsrover.enums.Direction;
import org.marsrover.model.Coordinates;
import org.marsrover.model.Position;
import org.marsrover.model.RoverMovement;
import org.marsrover.utill.FileReader;
import org.marsrover.utill.Rules;

import java.util.ArrayList;
import java.util.HashMap;

import static org.marsrover.utill.Constants.*;

@Log4j2
public class Main {

    public static void main(String[] args) {

        ArrayList<String> inputData = FileReader.readInputFile(FILE_NAME);
        String[] convertedData = inputData.toArray(new String[0]);
        String[] coordinates = convertedData[0].split("\\s+");
        Coordinates borderCoordinates = new Coordinates(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        HashMap<Integer, RoverMovement> rovers = getRovers(inputData.toArray(new String[0]), borderCoordinates);
        processRoverMovement(rovers, borderCoordinates);

    }


    public static HashMap<Integer, RoverMovement> getRovers(String[] inputData, Coordinates borderCoordinates) {

        HashMap<Integer, RoverMovement> roverMap = new HashMap<>();
        log.info("Creating Rovers map");
        for (int i = 1; i < inputData.length; i++) {
            try {
                RoverMovement roverMovement = new RoverMovement();
                Position initialPosition = new Position();
                String movements = "";
                if (i % 2 == 1) {
                    String[] values = inputData[i].split("\\s+");
                    initialPosition.setCoordinates(new Coordinates(Integer.valueOf(values[0]), Integer.valueOf(values[1])));
                    if (!isValidCoordinates(values, borderCoordinates)) {
                        roverMovement.setMovementStatus(ERROR_INVALID_COORDINATES);
                    }

                    initialPosition.setDirection(values[2]);
                    roverMovement.setInitialPosition(initialPosition);
                    roverMap.put(i + 1, roverMovement);
                } else {
                    if (roverMap.get(i) != null) {
                        movements = inputData[i];
                        roverMap.get(i).setMovements(movements);
                    }
                }
            } catch (Exception e) {
                log.error("Exception occured while creating rover map", e);
            }
        }
        log.info("End of creating map of Rovers");
        return roverMap;
    }

    private static boolean isValidCoordinates(String[] values, Coordinates borderCoordinates) {
        boolean isValid = true;
        if (Integer.parseInt(values[0]) < 0 || Integer.parseInt(values[1]) < 0
                || Integer.parseInt(values[0]) > borderCoordinates.getX() || Integer.parseInt(values[1]) > borderCoordinates.getY()) {
            return false;
        }
        return isValid;
    }


    public static void processRoverMovement(HashMap<Integer, RoverMovement> roverMap, Coordinates borderCoordinates) {
        roverMap.values().stream().forEach(r -> {
            Position position = getFinalPosition(r.getInitialPosition(), r.getMovements());
            if (!SUCCESS.equalsIgnoreCase(r.getMovementStatus())) {
                log.error("Rover landed on an invalid position");
            }
            if (checkValidPosition(position, borderCoordinates)) {
                log.info(position.getCoordinates().getX() + " " + position.getCoordinates().getY() + " " + position.getDirection());
            } else {
                log.error("Rover has moved out of the plateau");
            }
        });
    }

    public static boolean checkValidPosition(Position position, Coordinates borderCoordinates) {
        if (position.getCoordinates().compareTo(borderCoordinates) == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static Position getFinalPosition(Position initialPosition, String movements) {
        char[] moves = movements.toCharArray();
        String currentDirection = initialPosition.getDirection();
        Coordinates currentCoordinates = initialPosition.getCoordinates();
        Position finalPosition = new Position();
        for (char move : moves) {
            if (move == LEFT || move == RIGHT) {
                currentDirection = getDirection(currentDirection, move);
            } else if (move == 'M') {
                currentCoordinates = getCoordinates(currentCoordinates, currentDirection);
            }
        }
        finalPosition.setDirection(currentDirection);
        finalPosition.setCoordinates(currentCoordinates);
        return finalPosition;
    }

    private static Coordinates getCoordinates(Coordinates currentCoordinates, String currentDirection) {
        int x = currentCoordinates.getX();
        int y = currentCoordinates.getY();
        switch (Direction.valueOf(currentDirection)) {
            case N:
                y = y + 1;
                break;
            case S:
                y = y - 1;
                break;
            case E:
                x = x + 1;
                break;
            case W:
                x = x - 1;
        }
        return new Coordinates(x, y);
    }

    private static String getDirection(String direction, char move) {
        return Rules.directionMap().get(direction + move);
    }


}