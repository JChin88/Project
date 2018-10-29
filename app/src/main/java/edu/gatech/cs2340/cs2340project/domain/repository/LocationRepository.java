package edu.gatech.cs2340.cs2340project.domain.repository;

import java.io.InputStream;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface LocationRepository {

    void getLocation(String key);

    void addLocations(InputStream locationDataFile);

    List<Location> getLocationList();
}
