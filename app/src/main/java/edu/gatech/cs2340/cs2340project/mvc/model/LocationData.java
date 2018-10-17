package edu.gatech.cs2340.cs2340project.mvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationData {
    private static HashMap<Integer, Location> locationData = new HashMap<>();

    public static HashMap<Integer, Location> getLocationData() {
        return locationData;
    }

    /**
     * Adds a new location to the database
     * @param location the location to be added
     * @return if the location was successfully added
     */
    public static boolean addLocation(Integer key, Location location){
        locationData.put(key, location);
        return false;
    }

    /**
     * find a location by id
     * @param key the location's key
     * @return the location associated with the key
     */
    public static Location getLocation(Integer key) {
        return locationData.get(key);
    }

    public static List<Location> getLocationList() {
        List<Location> locationList = new ArrayList<>(locationData.values());
        return locationList;
    }
}
