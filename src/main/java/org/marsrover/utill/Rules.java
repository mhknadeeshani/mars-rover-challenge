package org.marsrover.utill;

import java.util.HashMap;

public class Rules {


    public static HashMap<String,String> directionMap () {

        HashMap<String, String> directionMap = new HashMap<>();
        directionMap.put("NL","W");
        directionMap.put("NR","E");
        directionMap.put("EL","N");
        directionMap.put("ER","S");
        directionMap.put("SL","E");
        directionMap.put("SR","W");
        directionMap.put("WL","S");
        directionMap.put("WR","N");
        return directionMap;
    }
}
