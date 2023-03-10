package org.marsrover.Exceptions;

public class InvalidPositionException extends RuntimeException{

    public InvalidPositionException(String error){
        super(error);
    }
}
