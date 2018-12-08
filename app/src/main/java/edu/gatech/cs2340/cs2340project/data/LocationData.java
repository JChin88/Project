package edu.gatech.cs2340.cs2340project.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;


/**
 * @author Hoa V Luu
 */
public class LocationData {
    private static final HashMap<Integer, Location> locationData = new HashMap<>();

    private static HashMap<Integer, Location> getLocationData() {
        return locationData;
    }

    /**
     * Adds a new location to the database
     * @param key key want to get
     * @param location the location to be added
     */
    public static void addLocation(Integer key, Location location){
        locationData.put(key, location);
    }

    /**
     * find a location by id
     * @param key the location's key
     * @return the location associated with the key
     */
    public static edu.gatech.cs2340.cs2340project.domain.model.Location getLocation(Integer key) {
        return locationData.get(key);
    }

    /**
     * Get location list
     * @return list of location
     */
    public static List<Location> getLocationList() {
        return new ArrayList<>(locationData.values());
    }
}
