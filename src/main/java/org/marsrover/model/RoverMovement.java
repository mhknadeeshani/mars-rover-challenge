package org.marsrover.model;

public class RoverMovement {

    private Position initialPosition;

    private String movements;

    private String movementStatus;


    public RoverMovement() {
        this.movementStatus = "Success";
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    public String getMovementStatus() {
        return movementStatus;
    }

    public void setMovementStatus(String movementStatus) {
        this.movementStatus = movementStatus;
    }
}
