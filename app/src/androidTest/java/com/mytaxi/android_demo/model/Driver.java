package com.mytaxi.android_demo.model;

import java.util.HashMap;
/**
 * This is a model class holding the Driver Search Data
 */
public class Driver {
    private String initials;
    private String fullName;

    private static HashMap<String, Driver> drivers = null;

    private Driver(String initials, String fullName) {
        this.initials = initials;
        this.fullName = fullName;
    }

    private static void loadDrivers(){

        drivers = new HashMap<>();
        Driver driver = new Driver("sa", "Sarah Scott");
        Driver driver2 = new Driver("aa", "Aaron Lopez");
        drivers.put(Constants.VALID, driver);
        drivers.put(Constants.INVALID, driver2);

    }
    //Fetch the valid and invalid data for the Driver
    public static Driver getDriver (String driverType) {
        if (drivers == null )
            loadDrivers();
        Driver driver = drivers.get(driverType);
        return driver;
    }

    public String getInitials() {
        return initials;
    }

    public String getFullName() {
        return fullName;
    }
}
