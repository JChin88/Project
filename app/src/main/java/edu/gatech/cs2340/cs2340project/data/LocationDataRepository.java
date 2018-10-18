package edu.gatech.cs2340.cs2340project.data;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;


public class LocationDataRepository implements LocationRepository {


    @Override
    public Location getLocation(Integer key) {
        //Handle exception if need

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return LocationData.getLocation(key);
    }

    @Override
    public List<Location> getLocationList() {
        return null;
    }
}
