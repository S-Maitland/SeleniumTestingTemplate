package org.example.WebDrivers;

import java.util.ArrayList;
import java.util.List;

public enum DriverType {

    CHROME,
    FIREFOX,
    EDGE;


    public static List<String> getBrowsers(){
        ArrayList<String> browserList = new ArrayList<>();
        for(DriverType driverType : DriverType.values()){
            browserList.add(driverType.name());
        }
        return browserList;
    }

}
