package org.marsrover.utill;

import lombok.extern.log4j.Log4j2;
import org.marsrover.Exceptions.InvalidDataException;
import org.marsrover.Exceptions.InvalidFilePathException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.marsrover.utill.Constants.ERROR_INVALID_DATA;
import static org.marsrover.utill.Constants.ERROR_INVALID_FILE_PATH;

@Log4j2
public class FileReader {


    public static ArrayList<String> readInputFile(String filePath){
        ArrayList<String> inputData =  new ArrayList<>();
        if(filePath == null && filePath.isEmpty()|| "".equals(filePath)){
            throw new InvalidFilePathException(ERROR_INVALID_FILE_PATH);
        }
        try{
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data == null && data.isEmpty()|| "".equals(data)){
                    throw new InvalidDataException(ERROR_INVALID_DATA);
                }
                inputData.add(data);
            }
            myReader.close();

        }catch (FileNotFoundException e){
            log.error("Exception occurred while reading file "+e.getMessage());
        }
        return inputData;
    }
}
