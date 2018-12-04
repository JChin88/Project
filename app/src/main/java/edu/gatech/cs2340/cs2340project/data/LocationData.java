package edu.gatech.cs2340.cs2340project.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;


/**
 * @author Hoa V Luu
 */
public class LocationData {
    private static final HashMap<Integer, DonationLocation> locationData = new HashMap<>();

    private static HashMap<Integer, DonationLocation> getLocationData() {
        return locationData;
    }

    /**
     * Adds a new location to the database
     * @param key key want to get
     * @param location the location to be added
     */
    public static void addLocation(Integer key, DonationLocation location){
        locationData.put(key, location);
    }

    /**
     * find a location by id
     * @param key the location's key
     * @return the location associated with the key
     */
    public static DonationLocation getLocation(Integer key) {
        return locationData.get(key);
    }

    /**
     * Get location list
     * @return list of location
     */
    public static List<DonationLocation> getLocationList() {
        return new ArrayList<>(locationData.values());
    }
}
