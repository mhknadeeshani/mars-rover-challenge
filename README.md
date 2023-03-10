
## Requirements

For building and running the application you need: 

JDK 1.8 or higher, Maven 4

## Run the application locally.

Run the Main class which is located at _src/main/java/org/marsrover/Main.java._

Input file - input.txt is at the source directory of src folder

* Input file consist of 
  * success scenario
  * Invalid initial landed position.
  * Moved out from plateau after processing the movements.
  

## Run the test cases.

Run the MasRoverMovementTest under the location of _src/test/java/org/marsrover/MasRoverMovementTest.java_

Input file - input.txt is at the source directory of test/resource folder

## Assumptions

* Movement strings("LMRR") only comes with 'L','R','M'. <br />
* Input records will enter line by line without empty lines. Otherwise, InvalidDataException will throw. <br />
* Rover can move upto border coordinates (inclusive) <br />


