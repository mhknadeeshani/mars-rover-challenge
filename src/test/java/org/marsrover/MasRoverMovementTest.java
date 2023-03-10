package org.marsrover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.marsrover.model.Coordinates;
import org.marsrover.model.Position;
import org.marsrover.model.RoverMovement;
import org.marsrover.utill.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.marsrover.Constants.TEST_ERROR_FILE_NAME;
import static org.marsrover.Constants.TEST_FILE_NAME;
import static org.junit.jupiter.api.Assertions.*;

public class MasRoverMovementTest {

    private static ArrayList<String> inputData;
    private static ArrayList<String> inputErrorData;

    private static Coordinates  borderCoordinates;

    private static List<Position> positions = new ArrayList<>();



    @BeforeAll
    public static void setUp() {
        inputData = FileReader.readInputFile(TEST_FILE_NAME);
        String[] convertedData = inputData.toArray(new String[0]);
        String[] coordinates = convertedData[0].split("\\s+");
        borderCoordinates = new Coordinates(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        inputErrorData = FileReader.readInputFile(TEST_ERROR_FILE_NAME);
        positions.add(new Position(new Coordinates(1,3),"N"));
        positions.add(new Position(new Coordinates(5,1),"E"));
    }

    @Test
    public void testGetRovers() {
        HashMap<Integer, RoverMovement> rovers = Main.getRovers(inputData.toArray(new String[0]), borderCoordinates);
        assertNotNull(rovers);
        assertEquals(2, rovers.size());
    }

    @Test
    public void testProcessRoverMovement() {
    }

    @Test
    public void testCheckValidPosition() {

        Coordinates coordinates = new Coordinates(1,2);
        Position p = new Position(coordinates, "N");
        boolean result = Main.checkValidPosition(p, borderCoordinates);
        assertTrue(result);

    }

    @Test
    public void validateFinalLocation() {

        HashMap<Integer, RoverMovement> rovers = Main.getRovers(inputData.toArray(new String[0]), borderCoordinates);
        List<Position> positionList =  new ArrayList<>();
        rovers.values().forEach(r-> {
            positionList.add(Main.getFinalPosition(r.getInitialPosition(), r.getMovements()));
            });
        assertTrue(positions.size() == positionList.size());
        assertTrue(positions.containsAll(positionList) && positionList.containsAll(positions));
    }

}
